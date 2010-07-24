package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ErrMsgKeyword;
import com.puresol.coding.lang.fortran.source.keywords.StatKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R641 dealloc-opt is STAT = stat-variable
 * or ERRMSG = errmsg-variable
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DeallocOpt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(ErrMsgKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ErrmsgVariable.class);
		} else if (acceptToken(StatKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(StatVariable.class);
		} else {
			abort();
		}
		finish();
	}
}
