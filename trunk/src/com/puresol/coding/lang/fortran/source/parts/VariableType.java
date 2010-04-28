package com.puresol.coding.lang.fortran.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.cpp.source.symbols.LParen;
import com.puresol.coding.lang.cpp.source.symbols.RParen;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.CharacterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ComplexKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RealKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableType extends AbstractFortranParser {

	private static final long serialVersionUID = -8542025375316127415L;

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(RealKeyword.class, DoubleKeyword.class,
				ComplexKeyword.class, CharacterKeyword.class);
		if (isToken(LParen.class)) {
			skipNested(LParen.class, RParen.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
