package com.puresol.coding.lang.fortran.source.grammar.programmunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.BlockDataKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1121 block-data-stmt is BLOCK DATA [ block-data-name ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BlockDataStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(BlockDataKeyword.class);
		acceptToken(NameLiteral.class);
		finish();
	}

}
