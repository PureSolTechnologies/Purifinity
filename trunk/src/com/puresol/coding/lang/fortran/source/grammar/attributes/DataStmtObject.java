package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.Variable;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R536 data-stmt-object is variable
 * or data-implied-do
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataStmtObject extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Variable.class) != null) {
		} else if (acceptPart(DataImpliedDo.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
