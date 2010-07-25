package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R919 io-implied-do-object is input-item
 * or output-item
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IoImpliedDoObject extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(InputItem.class) != null) {

		} else if (acceptPart(OutputItem.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
