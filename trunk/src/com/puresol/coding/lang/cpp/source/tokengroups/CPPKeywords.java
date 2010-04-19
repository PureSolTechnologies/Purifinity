package com.puresol.coding.lang.cpp.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

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
import com.puresol.parser.TokenDefinition;

public class CPPKeywords {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(PPIncludeKeyword.class);
	DEFINITIONS.add(PPDefineKeyword.class);
	DEFINITIONS.add(PPIfDefKeyword.class);
	DEFINITIONS.add(PPEndIfKeyword.class);
	DEFINITIONS.add(PPElseKeyword.class);

	DEFINITIONS.add(ClassKeyword.class);
	DEFINITIONS.add(NewKeyword.class);

	DEFINITIONS.add(PublicKeyword.class);
	DEFINITIONS.add(ProtectedKeyword.class);
	DEFINITIONS.add(PrivateKeyword.class);
	DEFINITIONS.add(StaticKeyword.class);

	DEFINITIONS.add(ReturnKeyword.class);
	DEFINITIONS.add(BreakKeyword.class);
	DEFINITIONS.add(ContinueKeyword.class);

	DEFINITIONS.add(IfKeyword.class);
	DEFINITIONS.add(ElseKeyword.class);
	DEFINITIONS.add(WhileKeyword.class);
	DEFINITIONS.add(DoKeyword.class);
	DEFINITIONS.add(ForKeyword.class);
	DEFINITIONS.add(SwitchKeyword.class);
	DEFINITIONS.add(CaseKeyword.class);

	DEFINITIONS.add(ThisKeyword.class);
	DEFINITIONS.add(NullKeyword.class);

	DEFINITIONS.add(VoidKeyword.class);
	DEFINITIONS.add(ConstKeyword.class);
	DEFINITIONS.add(BoolKeyword.class);
	DEFINITIONS.add(CharKeyword.class);
	DEFINITIONS.add(IntKeyword.class);
	DEFINITIONS.add(LongKeyword.class);
	DEFINITIONS.add(FloatKeyword.class);
	DEFINITIONS.add(DoubleKeyword.class);
    }
}
