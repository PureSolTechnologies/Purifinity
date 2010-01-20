package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.java.source.keywords.EnumKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.PartDoesNotMatchException;

public class EnumDeclaration extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	while (processPartIfPossible(Annotation.class))
	    ;
	processPart(ClassModifiers.class);
	processToken(EnumKeyword.class);
	if (isToken(LessThan.class)) {
	    processToken(LessThan.class);
	    processToken(IdLiteral.class);
	    while (processTokenIfPossible(Comma.class)) {
		processToken(LessThan.class);
	    }
	    processToken(GreaterThan.class);
	}
	String name = getCurrentToken().getText();
	processToken(IdLiteral.class);
	skipNested(LCurlyBracket.class, RCurlyBracket.class);
	int startPosition = getStartPositionWithLeadingHidden();
	int stopPosition = getPositionOfLastVisible();
	stopPosition = this.getPositionOfNextLineBreak(stopPosition);
	addCodeRange(new CodeRange(getTokenStream().getFile(),
		CodeRangeType.ENUMERATION, name, getTokenStream(),
		startPosition, stopPosition));
    }
}
