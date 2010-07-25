package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R901 io-unit is file-unit-number
 * or *
 * or internal-file-variable
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IoUnit extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(FileUnitNumber.class) != null) {
		} else if (acceptToken(Star.class) != null) {
		} else if (acceptPart(InternalFileVariable.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
