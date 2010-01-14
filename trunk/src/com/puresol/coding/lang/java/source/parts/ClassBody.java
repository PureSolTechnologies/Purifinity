package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassBody extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	processToken(LCurlyBracket.class);

	while ((processPartIfPossible(FieldDeclaration.class))
		|| (processPartIfPossible(MethodDeclaration.class))
		|| (processPartIfPossible(ConstructorDeclaration.class))
		|| (processPartIfPossible(StaticBlock.class))) {

	}

	processToken(RCurlyBracket.class);
    }
}
