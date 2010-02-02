package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Generic extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	skipNested(LessThan.class, GreaterThan.class); // TODO
    }

}
