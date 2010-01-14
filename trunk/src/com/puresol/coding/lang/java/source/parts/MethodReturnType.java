package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodReturnType extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	if (processTokenIfPossible(VoidKeyword.class)) {
	    return;
	}
	processPart(VariableType.class);
    }

}
