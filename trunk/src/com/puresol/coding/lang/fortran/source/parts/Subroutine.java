package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.cpp.source.symbols.LParen;
import com.puresol.coding.lang.cpp.source.symbols.RParen;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndSubroutineKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SubroutineKeyword;
import com.puresol.coding.langelements.SubroutineLanguageElement;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Subroutine extends AbstractFortranParser implements
		SubroutineLanguageElement {

	private static final long serialVersionUID = -8790882661090444385L;

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(SubroutineKeyword.class);

		String name = getCurrentToken().getText();
		expectToken(name);

		if (isToken(LParen.class)) {
			skipNested(LParen.class, RParen.class);
		}

		// TODO read here the code...
		skipTo(EndKeyword.class, EndSubroutineKeyword.class);

		expectToken(EndKeyword.class, EndSubroutineKeyword.class);
		acceptToken(name);

		finish(name);
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.SUBROUTINE;
	}
}
