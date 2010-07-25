package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.SyncAllKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R858 sync-all-stmt is SYNC ALL [ ( [ sync-stat-list ] ) ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SyncAllStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(SyncAllKeyword.class);
		if (acceptToken(LParen.class) != null) {
			acceptPart(SyncStatList.class);
			expectToken(RParen.class);
		}
		finish();
	}
}
