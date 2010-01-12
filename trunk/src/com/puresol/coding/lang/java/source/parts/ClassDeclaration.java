package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.ImplementsKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.coding.lang.java.source.tokengroups.ClassModifiers;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class ClassDeclaration extends AbstractSourceCodeParser {

	public ClassDeclaration(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		int startPosition = getCurrentPosition();
		processOneTokenOf(ClassModifiers.class);
		while (isOneTokenOf(ClassModifiers.class)) {
			processOneTokenOf(ClassModifiers.class);
		}
		processToken(ClassKeyword.class);
		String name = getCurrentToken().getText();
		processToken(IdLiteral.class);
		if (isToken(ExtendsKeyword.class)) {
			processToken(ExtendsKeyword.class);
			processToken(IdLiteral.class);
		}
		if (isToken(ImplementsKeyword.class)) {
			processToken(ImplementsKeyword.class);
			processToken(IdLiteral.class);
			while (isToken(Comma.class)) {
				processToken(Comma.class);
				processToken(IdLiteral.class);
			}
		}
		if (!isToken(LCurlyBracket.class)) {
			abort();
		}
		skipNested(LCurlyBracket.class, RCurlyBracket.class);
		int stopPosition = getCurrentPosition();
		addCodeRange(new CodeRange(getTokenStream().getName(),
				CodeRangeType.CLASS, name, getTokenStream(), startPosition,
				stopPosition));
	}
}
