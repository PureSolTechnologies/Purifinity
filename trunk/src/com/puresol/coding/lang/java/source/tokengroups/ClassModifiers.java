package com.puresol.coding.lang.java.source.tokengroups;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.java.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class ClassModifiers extends AbstractTokenDefinitionGroup {

    private static final Logger logger =
	    Logger.getLogger(ClassModifiers.class);

    @Override
    protected void initialize() {
	try {
	    addTokenDefinition(PublicKeyword.class);
	    addTokenDefinition(ProtectedKeyword.class);
	    addTokenDefinition(FinalKeyword.class);
	    addTokenDefinition(AbstractKeyword.class);
	} catch (TokenException e) {
	    logger.error(e.getMessage());
	}
    }

}
