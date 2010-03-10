package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.coding.lang.java.source.tokengroups.Primitives;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableType extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (isCurrentOneOf(Primitives.class)) {
	    processOneOf(Primitives.class);
	} else {
	    processPart(ObjectType.class);
	}
	processPartIfPossible(Generic.class);
	if (isToken(LRectBracket.class)) {
	    processToken(LRectBracket.class);
	    processToken(RRectBracket.class);
	}
    }

}
