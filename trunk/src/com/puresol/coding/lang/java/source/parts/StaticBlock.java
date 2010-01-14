package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.parser.PartDoesNotMatchException;

public class StaticBlock extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	processToken(StaticKeyword.class);
	processPart(CodeBlock.class);
    }

}
