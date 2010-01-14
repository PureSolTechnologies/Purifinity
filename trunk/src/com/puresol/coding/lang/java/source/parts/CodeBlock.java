package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.PartDoesNotMatchException;

public class CodeBlock extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	skipNested(LCurlyBracket.class, RCurlyBracket.class);
    }

}
