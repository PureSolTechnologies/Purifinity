package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.parser.TokenDefinition;

public class ClassModifiers {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	DEFINITIONS.add(PublicKeyword.class);
	DEFINITIONS.add(ProtectedKeyword.class);
	DEFINITIONS.add(FinalKeyword.class);
	DEFINITIONS.add(AbstractKeyword.class);
    }

}
