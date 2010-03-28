package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.coderanges.FortranProgram;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndProgramKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProgramKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Program extends AbstractSourceCodeParser {

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		processToken(ProgramKeyword.class);

		String name = getCurrentToken().getText();
		processToken(name);

		// TODO read here the code...
		skipTokensUntil(EndKeyword.class, EndProgramKeyword.class);

		processToken(EndKeyword.class, EndProgramKeyword.class);
		processTokenIfPossible(name);

		int startPosition = getStartPositionWithLeadingHidden();
		int stopPosition = getPositionOfLastVisible();
		stopPosition = this.getPositionOfNextLineBreak(stopPosition);

		addCodeRange(new FortranProgram(name, getTokenStream(), startPosition,
				stopPosition));
	}
}
