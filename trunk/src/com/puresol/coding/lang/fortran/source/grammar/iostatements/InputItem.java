package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.Variable;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R916 input-item is variable
 * or io-implied-do
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InputItem extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Variable.class) != null) {
		} else if (acceptPart(IoImpliedDo.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
