package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ErrmsgVariable;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.StatVariable;
import com.puresol.coding.lang.fortran.source.keywords.ErrMsgKeyword;
import com.puresol.coding.lang.fortran.source.keywords.StatKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R859 sync-stat is STAT = stat-variable
 * or ERRMSG = errmsg-variable
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SyncStat extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(StatKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(StatVariable.class);
		} else if (acceptToken(ErrMsgKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ErrmsgVariable.class);
		} else {
			abort();
		}
		finish();
	}
}
