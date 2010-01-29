package com.puresol.coding.lang.fortran.source.tokengroups;

import com.puresol.coding.lang.fortran.source.keywords.CallKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CaseDefaultKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CaseKeyword;
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
import com.puresol.coding.lang.fortran.source.keywords.ElseKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ElseWhereKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndDoKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndForAllKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndFunctionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndProgramKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndSubroutineKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExitKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExternalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ForAllKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FunctionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IfKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntegerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntrinsicKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ParameterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PointerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProgramKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SubroutineKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SwitchCaseKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ThenKeyword;
import com.puresol.coding.lang.fortran.source.keywords.WhereKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class FortranKeywords extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addTokenDefinition(ProgramKeyword.class);
	addTokenDefinition(SubroutineKeyword.class);
	addTokenDefinition(FunctionKeyword.class);
	addTokenDefinition(EndProgramKeyword.class);
	addTokenDefinition(EndSubroutineKeyword.class);
	addTokenDefinition(EndFunctionKeyword.class);

	addTokenDefinition(ForAllKeyword.class);
	addTokenDefinition(ContinueKeyword.class);
	addTokenDefinition(DoWhileKeyword.class);
	addTokenDefinition(DoKeyword.class);
	addTokenDefinition(IfKeyword.class);
	addTokenDefinition(ThenKeyword.class);
	addTokenDefinition(EndDoKeyword.class);
	addTokenDefinition(EndForAllKeyword.class);
	addTokenDefinition(EndKeyword.class);
	addTokenDefinition(ElseKeyword.class);
	addTokenDefinition(ExitKeyword.class);
	addTokenDefinition(CycleKeyword.class);

	addTokenDefinition(CallKeyword.class);
	addTokenDefinition(ReturnKeyword.class);

	addTokenDefinition(SwitchCaseKeyword.class);
	addTokenDefinition(CaseDefaultKeyword.class);
	addTokenDefinition(CaseKeyword.class);
	addTokenDefinition(WhereKeyword.class);
	addTokenDefinition(ElseWhereKeyword.class);

	addTokenDefinition(ParameterKeyword.class);
	addTokenDefinition(ExternalKeyword.class);
	addTokenDefinition(IntrinsicKeyword.class);

	addTokenDefinition(PointerKeyword.class);
	addTokenDefinition(DimensionKeyword.class);

	addTokenDefinition(ComplexKeyword.class);
	addTokenDefinition(DoubleKeyword.class);
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

    }
}
