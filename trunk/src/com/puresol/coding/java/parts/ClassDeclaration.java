package com.puresol.coding.java.parts;

import com.puresol.coding.AbstractSourceCodePart;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.SourceCodeParser;
import com.puresol.coding.java.keywords.ClassKeyword;
import com.puresol.coding.java.keywords.ExtendsKeyword;
import com.puresol.coding.java.keywords.ImplementsKeyword;
import com.puresol.coding.java.tokengroups.ClassModifiers;
import com.puresol.coding.java.tokens.Comma;
import com.puresol.coding.java.tokens.IdLiteral;
import com.puresol.coding.java.tokens.LCurlyBracket;
import com.puresol.coding.java.tokens.RCurlyBracket;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class ClassDeclaration extends AbstractSourceCodePart {

    public ClassDeclaration(SourceCodeParser parser,
	    TokenStream tokenStream, int startPos) {
	super(parser, tokenStream, startPos);
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
		CodeRangeType.CLASS, name, getTokenStream(),
		startPosition, stopPosition));
    }
}
