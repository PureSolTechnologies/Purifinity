package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.cpp.source.symbols.LParen;
import com.puresol.coding.lang.cpp.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.keywords.EndFunctionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FunctionKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Function extends AbstractSourceCodeParser {

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		processToken(FunctionKeyword.class);

		String name = getCurrentToken().getText();
		processToken(name);

		this.skipNested(LParen.class, RParen.class);

		// TODO read here the code...
		this.skipTokensUntil(EndKeyword.class, EndFunctionKeyword.class);

		processToken(EndKeyword.class, EndFunctionKeyword.class);
	}
}
