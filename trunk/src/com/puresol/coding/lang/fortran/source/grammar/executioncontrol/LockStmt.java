package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.LockKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R863 lock-stmt is LOCK ( lock-variable [ , lock-stat-list ] )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LockStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LockKeyword.class);
		expectToken(LParen.class);
		expectPart(LockVariable.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(LockStatList.class);
		}
		expectToken(RParen.class);
		finish();
	}
}
