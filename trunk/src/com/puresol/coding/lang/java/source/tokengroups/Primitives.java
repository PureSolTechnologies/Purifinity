package com.puresol.coding.lang.java.source.tokengroups;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class Primitives extends AbstractTokenDefinitionGroup {

    private static final Logger logger =
	    Logger.getLogger(Primitives.class);

    @Override
    protected void initialize() {
	try {
	    addTokenDefinition(BooleanKeyword.class);
	    addTokenDefinition(CharKeyword.class);
	    addTokenDefinition(ByteKeyword.class);
	    addTokenDefinition(ShortKeyword.class);
	    addTokenDefinition(IntKeyword.class);
	    addTokenDefinition(LongKeyword.class);
	    addTokenDefinition(VoidKeyword.class);
	} catch (TokenException e) {
	    logger.error(e.getMessage());
	}
    }
}
