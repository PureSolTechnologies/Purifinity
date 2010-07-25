package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.LogicalVariable;
import com.puresol.coding.lang.fortran.source.keywords.AcquiredLockKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R864 lock-stat is ACQUIRED LOCK = scalar-logical-variable
 * or sync-stat
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LockStat extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(AcquiredLockKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(LogicalVariable.class);
		} else if (acceptPart(SyncStat.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
