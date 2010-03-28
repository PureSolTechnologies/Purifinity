package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.cpp.source.symbols.LParen;
import com.puresol.coding.lang.cpp.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.coderanges.FortranSubroutine;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndSubroutineKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SubroutineKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Subroutine extends AbstractSourceCodeParser {

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		processToken(SubroutineKeyword.class);

		String name = getCurrentToken().getText();
		processToken(name);

		if (isToken(LParen.class)) {
			skipNested(LParen.class, RParen.class);
		}

		// TODO read here the code...
		skipTokensUntil(EndKeyword.class, EndSubroutineKeyword.class);

		processToken(EndKeyword.class, EndSubroutineKeyword.class);
		processTokenIfPossible(name);

		int startPosition = getStartPositionWithLeadingHidden();
		int stopPosition = getPositionOfLastVisible();
		stopPosition = this.getPositionOfNextLineBreak(stopPosition);

		addCodeRange(new FortranSubroutine(name, getTokenStream(),
				startPosition, stopPosition));
	}
}
