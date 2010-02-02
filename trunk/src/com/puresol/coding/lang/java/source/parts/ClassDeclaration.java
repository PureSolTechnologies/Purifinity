package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.Language;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.ImplementsKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassDeclaration extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	while (processPartIfPossible(Annotation.class))
	    ;
	processPart(ClassModifiers.class);
	processToken(ClassKeyword.class);
	String name = getCurrentToken().getText();
	processToken(IdLiteral.class);
	processPartIfPossible(Generic.class);
	if (processTokenIfPossible(ExtendsKeyword.class)) {
	    processToken(IdLiteral.class);
	    processPartIfPossible(Generic.class);
	}
	if (processTokenIfPossible(ImplementsKeyword.class)) {
	    processToken(IdLiteral.class);
	    processPartIfPossible(Generic.class);
	    while (processTokenIfPossible(Comma.class)) {
		processToken(IdLiteral.class);
		processPartIfPossible(Generic.class);
	    }
	}
	processPart(ClassBody.class);
	int startPosition = getStartPositionWithLeadingHidden();
	int stopPosition = getPositionOfLastVisible();
	stopPosition = this.getPositionOfNextLineBreak(stopPosition);
	addCodeRange(Language.JAVA, CodeRangeType.CLASS, name,
		startPosition, stopPosition);
    }
}
