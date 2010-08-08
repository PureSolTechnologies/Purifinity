package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.NativeKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.StrictfpKeyword;
import com.puresol.coding.lang.java.source.keywords.SynchronizedKeyword;
import com.puresol.coding.lang.java.source.keywords.TransientKeyword;
import com.puresol.coding.lang.java.source.keywords.VolatileKeyword;
import com.puresol.parser.tokens.TokenDefinition;

/**
 * modifiers : ( annotation | 'public' | 'protected' | 'private' | 'static' |
 * 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' |
 * 'strictfp' )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Modifiers {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(PublicKeyword.class);
	DEFINITIONS.add(ProtectedKeyword.class);
	DEFINITIONS.add(PrivateKeyword.class);
	DEFINITIONS.add(StaticKeyword.class);
	DEFINITIONS.add(AbstractKeyword.class);
	DEFINITIONS.add(FinalKeyword.class);
	DEFINITIONS.add(NativeKeyword.class);
	DEFINITIONS.add(SynchronizedKeyword.class);
	DEFINITIONS.add(TransientKeyword.class);
	DEFINITIONS.add(VolatileKeyword.class);
	DEFINITIONS.add(StrictfpKeyword.class);
    }

}
