package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.coderanges.FortranModule;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndModuleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ModuleKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Module extends AbstractSourceCodeParser {

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		processToken(ModuleKeyword.class);

		String name = getCurrentToken().getText();
		processToken(name);

		// TODO read here the code...
		skipTokensUntil(EndKeyword.class, EndModuleKeyword.class);

		processToken(EndKeyword.class, EndModuleKeyword.class);
		processTokenIfPossible(name);

		int startPosition = getStartPositionWithLeadingHidden();
		int stopPosition = getPositionOfLastVisible();
		stopPosition = this.getPositionOfNextLineBreak(stopPosition);

		addCodeRange(new FortranModule(name, getTokenStream(), startPosition,
				stopPosition));
}
}
