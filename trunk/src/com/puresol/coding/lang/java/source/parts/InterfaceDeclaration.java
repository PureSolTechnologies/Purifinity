package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.coderanges.JavaInterface;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.InterfaceKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class InterfaceDeclaration extends AbstractSourceCodeParser {

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (acceptPart(Annotation.class))
			;
		expectPart(ClassModifiers.class);
		expectToken(InterfaceKeyword.class);
		String name = getCurrentToken().getText();
		expectToken(IdLiteral.class);
		acceptPart(Generic.class);
		if (acceptToken(ExtendsKeyword.class)) {
			expectToken(IdLiteral.class);
			acceptPart(Generic.class);
		}
		skipNested(LCurlyBracket.class, RCurlyBracket.class);
		int startPosition = getStartPositionWithLeadingHidden();
		int stopPosition = getPositionOfLastVisible();
		stopPosition = this.getPositionOfNextLineBreak(stopPosition);
		addCodeRange(new JavaInterface(name, getTokenStream(), startPosition,
				stopPosition));
	}
}
