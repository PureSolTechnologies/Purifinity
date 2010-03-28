package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.java.source.coderanges.JavaConstructor;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ConstructorDeclaration extends AbstractSourceCodeParser {

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		processPartIfPossible(ConstructorModifiers.class);
		String name = getCurrentToken().getText();
		processToken(IdLiteral.class);
		skipNested(LParen.class, RParen.class);
		processPartIfPossible(ThrowsDeclaration.class);
		processPart(CodeBlock.class);
		int startPosition = getStartPositionWithLeadingHidden();
		int stopPosition = getPositionOfLastVisible();
		stopPosition = this.getPositionOfNextLineBreak(stopPosition);
		addCodeRange(new JavaConstructor(name, getTokenStream(), startPosition,
				stopPosition));
	}

}
