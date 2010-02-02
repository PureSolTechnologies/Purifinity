package com.puresol.coding.lang.java.source.tokengroups;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.TransientKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class FieldModifiers extends AbstractTokenDefinitionGroup {

    private static final Logger logger =
	    Logger.getLogger(FieldModifiers.class);

    @Override
    protected void initialize() {
	try {
	    addTokenDefinition(PublicKeyword.class);
	    addTokenDefinition(ProtectedKeyword.class);
	    addTokenDefinition(PrivateKeyword.class);
	    addTokenDefinition(FinalKeyword.class);
	    addTokenDefinition(StaticKeyword.class);
	    addTokenDefinition(TransientKeyword.class);
	} catch (TokenException e) {
	    logger.error(e.getMessage());
	}
    }

}
