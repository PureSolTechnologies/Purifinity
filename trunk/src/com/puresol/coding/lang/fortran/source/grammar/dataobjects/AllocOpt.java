package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ErrMsgKeyword;
import com.puresol.coding.lang.fortran.source.keywords.MoldKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SourceKeyword;
import com.puresol.coding.lang.fortran.source.keywords.StatKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R627 alloc-opt is ERRMSG = errmsg-variable
 * or MOLD = source-expr
 * or SOURCE = source-expr
 * or STAT = stat-variable
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AllocOpt extends AbstractFortranParser {

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
		} else if (acceptToken(MoldKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(SourceExpr.class);
		} else if (acceptToken(SourceKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(SourceExpr.class);
		} else if (acceptToken(StatKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(StatVariable.class);
		} else {
			abort();
		}
		finish();
	}
}
