package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.SynchronizedKeyword;
import com.puresol.parser.TokenDefinition;

public class MethodModifiers {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(PublicKeyword.class);
	DEFINITIONS.add(ProtectedKeyword.class);
	DEFINITIONS.add(PrivateKeyword.class);
	DEFINITIONS.add(FinalKeyword.class);
	DEFINITIONS.add(StaticKeyword.class);
	DEFINITIONS.add(AbstractKeyword.class);
	DEFINITIONS.add(SynchronizedKeyword.class);
    }

}
