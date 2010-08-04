package com.puresol.coding.lang.fortran.source.grammar.programmunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.SpecificationPart;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1120 block-data is block-data-stmt
 * [ specification-part ]
 * end-block-data-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BlockData extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(BlockDataStmt.class);
		acceptPart(SpecificationPart.class);
		expectPart(EndBlockDataStmt.class);
		finish();
	}

}
