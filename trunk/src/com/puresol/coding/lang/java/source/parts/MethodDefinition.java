package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.keywords.ThrowsKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodDefinition extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	while (processPartIfPossible(Annotation.class))
	    ;
	processPartIfPossible(MethodModifiers.class);
	processPart(MethodReturnType.class);
	processToken(IdLiteral.class);
	skipNested(LParen.class, RParen.class);
	if (processTokenIfPossible(ThrowsKeyword.class)) {
	    skipTokensUntil(LCurlyBracket.class);
	}
	processToken(Semicolon.class);
    }

}
