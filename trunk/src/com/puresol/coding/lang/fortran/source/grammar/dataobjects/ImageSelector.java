package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.LBracket;
import com.puresol.coding.lang.fortran.source.symbols.RBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R624 image-selector is lbracket cosubscript-list rbracket
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ImageSelector extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LBracket.class);
		expectPart(CosubscriptList.class);
		expectToken(RBracket.class);
		finish();
	}
}
