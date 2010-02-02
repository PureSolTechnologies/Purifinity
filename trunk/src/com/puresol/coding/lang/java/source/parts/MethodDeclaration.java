package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.Language;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodDeclaration extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	while (processPartIfPossible(Annotation.class))
	    ;
	processPartIfPossible(MethodModifiers.class);
	processPart(MethodReturnType.class);
	String name = getCurrentToken().getText();
	processToken(IdLiteral.class);
	skipNested(LParen.class, RParen.class);
	processPartIfPossible(ThrowsDeclaration.class);
	processPart(CodeBlock.class);
	int startPosition = getStartPositionWithLeadingHidden();
	int stopPosition = getPositionOfLastVisible();
	stopPosition = this.getPositionOfNextLineBreak(stopPosition);
	addCodeRange(Language.JAVA, CodeRangeType.METHOD, name,
		startPosition, stopPosition);
    }

}
