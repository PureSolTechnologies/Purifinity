package com.puresol.reporting.html.css.parser.tokengroups;

import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class CSSSymbols extends AbstractTokenDefinitionGroup {

    public CSSSymbols() throws TokenException {
	super();
    }

    @Override
    protected void initialize() {
	try {
	    addTokenDefinition(LCurlyBracket.class);
	    addTokenDefinition(RCurlyBracket.class);
	    addTokenDefinition(Colon.class);
	    addTokenDefinition(Dot.class);
	} catch (TokenException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
