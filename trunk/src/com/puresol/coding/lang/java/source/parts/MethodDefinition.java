package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.keywords.ThrowsKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodDefinition extends AbstractSourceCodeParser {

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (acceptPart(Annotation.class))
			;
		acceptPart(MethodModifiers.class);
		expectPart(MethodReturnType.class);
		expectToken(IdLiteral.class);
		skipNested(LParen.class, RParen.class);
		if (acceptToken(ThrowsKeyword.class)) {
			skipTo(LCurlyBracket.class);
		}
		expectToken(Semicolon.class);
	}

}
