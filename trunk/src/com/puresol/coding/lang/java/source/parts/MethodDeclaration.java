package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ThrowsKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.parts.interfaces.NormalAnnotation;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = 7410581812232089806L;

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (acceptPart(NormalAnnotation.class) != null)
			;
		acceptPart(MethodModifiers.class);
		expectPart(MethodReturnType.class);
		expectToken(Identifier.class);
		skipNested(LParen.class, RParen.class);
		if (acceptToken(ThrowsKeyword.class) != null) {
			skipTo(LCurlyBracket.class);
		}
		expectToken(Semicolon.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
