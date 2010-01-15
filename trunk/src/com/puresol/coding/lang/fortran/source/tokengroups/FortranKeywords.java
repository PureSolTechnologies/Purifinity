package com.puresol.coding.lang.fortran.source.tokengroups;

import com.puresol.coding.lang.fortran.source.keywords.CallKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ComplexKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ContinueKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DoKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ElseKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExternalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IfKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntegerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntrinsicKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ParameterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SubRoutineKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ThenKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class FortranKeywords extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addTokenDefinition(SubRoutineKeyword.class);

	addTokenDefinition(ContinueKeyword.class);
	addTokenDefinition(DoKeyword.class);
	addTokenDefinition(IfKeyword.class);
	addTokenDefinition(ThenKeyword.class);
	addTokenDefinition(EndKeyword.class);
	addTokenDefinition(ElseKeyword.class);

	addTokenDefinition(CallKeyword.class);
	addTokenDefinition(ReturnKeyword.class);

	addTokenDefinition(ParameterKeyword.class);
	addTokenDefinition(ExternalKeyword.class);
	addTokenDefinition(IntrinsicKeyword.class);

	addTokenDefinition(ComplexKeyword.class);
	addTokenDefinition(DoubleKeyword.class);
	addTokenDefinition(IntegerKeyword.class);

    }
}
