package com.puresol.coding.lang.fortran.source.grammar.programmunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.BlockDataKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1122 end-block-data-stmt is END [ BLOCK DATA [ block-data-name ] ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndBlockDataStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(EndKeyword.class);
		if (acceptToken(BlockDataKeyword.class) != null) {
			acceptToken(NameLiteral.class);
		}
		finish();
	}

}
