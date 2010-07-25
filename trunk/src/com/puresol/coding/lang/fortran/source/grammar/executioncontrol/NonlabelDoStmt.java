package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.DoKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R817 nonlabel-do-stmt is [ do-construct-name : ] DO [ loop-control ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NonlabelDoStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DoConstructName.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(DoKeyword.class);
		acceptPart(LoopControl.class);
		finish();
	}
}
