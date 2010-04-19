package com.puresol.coding.lang.fortran.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

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
import com.puresol.parser.TokenDefinition;

public class FortranKeywords {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(ProgramKeyword.class);
	DEFINITIONS.add(SubroutineKeyword.class);
	DEFINITIONS.add(FunctionKeyword.class);
	DEFINITIONS.add(ModuleKeyword.class);
	DEFINITIONS.add(EndProgramKeyword.class);
	DEFINITIONS.add(EndSubroutineKeyword.class);
	DEFINITIONS.add(EndFunctionKeyword.class);
	DEFINITIONS.add(EndModuleKeyword.class);

	DEFINITIONS.add(ForAllKeyword.class);
	DEFINITIONS.add(ContinueKeyword.class);
	DEFINITIONS.add(DoWhileKeyword.class);
	DEFINITIONS.add(DoKeyword.class);
	DEFINITIONS.add(IfKeyword.class);
	DEFINITIONS.add(ThenKeyword.class);
	DEFINITIONS.add(EndDoKeyword.class);
	DEFINITIONS.add(EndIfKeyword.class);
	DEFINITIONS.add(EndForAllKeyword.class);
	DEFINITIONS.add(EndKeyword.class);
	DEFINITIONS.add(ElseIfKeyword.class);
	DEFINITIONS.add(ElseKeyword.class);
	DEFINITIONS.add(ExitKeyword.class);
	DEFINITIONS.add(StopKeyword.class);
	DEFINITIONS.add(CycleKeyword.class);
	DEFINITIONS.add(GotoKeyword.class);

	DEFINITIONS.add(CallKeyword.class);
	DEFINITIONS.add(ReturnKeyword.class);

	DEFINITIONS.add(ReadKeyword.class);
	DEFINITIONS.add(WriteKeyword.class);
	DEFINITIONS.add(RewindKeyword.class);

	DEFINITIONS.add(SwitchCaseKeyword.class);
	DEFINITIONS.add(CaseDefaultKeyword.class);
	DEFINITIONS.add(CaseKeyword.class);
	DEFINITIONS.add(WhereKeyword.class);
	DEFINITIONS.add(ElseWhereKeyword.class);

	DEFINITIONS.add(ParameterKeyword.class);
	DEFINITIONS.add(ExternalKeyword.class);
	DEFINITIONS.add(IntrinsicKeyword.class);
	DEFINITIONS.add(ImplicitKeyword.class);
	DEFINITIONS.add(NoneKeyword.class);

	DEFINITIONS.add(PointerKeyword.class);
	DEFINITIONS.add(DimensionKeyword.class);

	DEFINITIONS.add(ComplexKeyword.class);
	DEFINITIONS.add(CharacterKeyword.class);
	DEFINITIONS.add(DoubleKeyword.class);
	DEFINITIONS.add(RealKeyword.class);
	DEFINITIONS.add(IntegerKeyword.class);

	DEFINITIONS.add(DotEQDot.class);
	DEFINITIONS.add(DotNEDot.class);
	DEFINITIONS.add(DotLTDot.class);
	DEFINITIONS.add(DotGTDot.class);
	DEFINITIONS.add(DotLEDot.class);
	DEFINITIONS.add(DotGEDot.class);

	DEFINITIONS.add(DotORDot.class);
	DEFINITIONS.add(DotANDDot.class);
	DEFINITIONS.add(DotNOTDot.class);
	DEFINITIONS.add(DotTRUEDot.class);
	DEFINITIONS.add(DotFALSEDot.class);
    }
}
