package com.puresol.coding.lang.fortran.source.tokengroups;

import org.apache.log4j.Logger;

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
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class FortranKeywords extends AbstractTokenDefinitionGroup {

	public static final FortranKeywords INSTANCE = new FortranKeywords();

	private static final Logger logger = Logger
			.getLogger(FortranKeywords.class);

	@Override
	protected void initialize() {
		try {
			addTokenDefinition(ProgramKeyword.class);
			addTokenDefinition(SubroutineKeyword.class);
			addTokenDefinition(FunctionKeyword.class);
			addTokenDefinition(ModuleKeyword.class);
			addTokenDefinition(EndProgramKeyword.class);
			addTokenDefinition(EndSubroutineKeyword.class);
			addTokenDefinition(EndFunctionKeyword.class);
			addTokenDefinition(EndModuleKeyword.class);

			addTokenDefinition(ForAllKeyword.class);
			addTokenDefinition(ContinueKeyword.class);
			addTokenDefinition(DoWhileKeyword.class);
			addTokenDefinition(DoKeyword.class);
			addTokenDefinition(IfKeyword.class);
			addTokenDefinition(ThenKeyword.class);
			addTokenDefinition(EndDoKeyword.class);
			addTokenDefinition(EndIfKeyword.class);
			addTokenDefinition(EndForAllKeyword.class);
			addTokenDefinition(EndKeyword.class);
			addTokenDefinition(ElseIfKeyword.class);
			addTokenDefinition(ElseKeyword.class);
			addTokenDefinition(ExitKeyword.class);
			addTokenDefinition(StopKeyword.class);
			addTokenDefinition(CycleKeyword.class);
			addTokenDefinition(GotoKeyword.class);

			addTokenDefinition(CallKeyword.class);
			addTokenDefinition(ReturnKeyword.class);

			addTokenDefinition(ReadKeyword.class);
			addTokenDefinition(WriteKeyword.class);
			addTokenDefinition(RewindKeyword.class);

			addTokenDefinition(SwitchCaseKeyword.class);
			addTokenDefinition(CaseDefaultKeyword.class);
			addTokenDefinition(CaseKeyword.class);
			addTokenDefinition(WhereKeyword.class);
			addTokenDefinition(ElseWhereKeyword.class);

			addTokenDefinition(ParameterKeyword.class);
			addTokenDefinition(ExternalKeyword.class);
			addTokenDefinition(IntrinsicKeyword.class);
			addTokenDefinition(ImplicitKeyword.class);
			addTokenDefinition(NoneKeyword.class);

			addTokenDefinition(PointerKeyword.class);
			addTokenDefinition(DimensionKeyword.class);

			addTokenDefinition(ComplexKeyword.class);
			addTokenDefinition(CharacterKeyword.class);
			addTokenDefinition(DoubleKeyword.class);
			addTokenDefinition(RealKeyword.class);
			addTokenDefinition(IntegerKeyword.class);

			addTokenDefinition(DotEQDot.class);
			addTokenDefinition(DotNEDot.class);
			addTokenDefinition(DotLTDot.class);
			addTokenDefinition(DotGTDot.class);
			addTokenDefinition(DotLEDot.class);
			addTokenDefinition(DotGEDot.class);

			addTokenDefinition(DotORDot.class);
			addTokenDefinition(DotANDDot.class);
			addTokenDefinition(DotNOTDot.class);
			addTokenDefinition(DotTRUEDot.class);
			addTokenDefinition(DotFALSEDot.class);
		} catch (TokenException e) {
			logger.error(e.getMessage());
		}
	}
}
