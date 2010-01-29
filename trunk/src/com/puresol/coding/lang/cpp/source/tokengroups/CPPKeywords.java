package com.puresol.coding.lang.cpp.source.tokengroups;

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
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class CPPKeywords extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {

	addTokenDefinition(PPIncludeKeyword.class);
	addTokenDefinition(PPDefineKeyword.class);
	addTokenDefinition(PPIfDefKeyword.class);
	addTokenDefinition(PPEndIfKeyword.class);
	addTokenDefinition(PPElseKeyword.class);

	addTokenDefinition(ClassKeyword.class);
	addTokenDefinition(NewKeyword.class);

	addTokenDefinition(PublicKeyword.class);
	addTokenDefinition(ProtectedKeyword.class);
	addTokenDefinition(PrivateKeyword.class);
	addTokenDefinition(StaticKeyword.class);

	addTokenDefinition(ReturnKeyword.class);
	addTokenDefinition(BreakKeyword.class);
	addTokenDefinition(ContinueKeyword.class);

	addTokenDefinition(IfKeyword.class);
	addTokenDefinition(ElseKeyword.class);
	addTokenDefinition(WhileKeyword.class);
	addTokenDefinition(DoKeyword.class);
	addTokenDefinition(ForKeyword.class);
	addTokenDefinition(SwitchKeyword.class);
	addTokenDefinition(CaseKeyword.class);

	addTokenDefinition(ThisKeyword.class);
	addTokenDefinition(NullKeyword.class);

	addTokenDefinition(VoidKeyword.class);
	addTokenDefinition(ConstKeyword.class);
	addTokenDefinition(BoolKeyword.class);
	addTokenDefinition(CharKeyword.class);
	addTokenDefinition(IntKeyword.class);
	addTokenDefinition(LongKeyword.class);
	addTokenDefinition(FloatKeyword.class);
	addTokenDefinition(DoubleKeyword.class);
    }
}
