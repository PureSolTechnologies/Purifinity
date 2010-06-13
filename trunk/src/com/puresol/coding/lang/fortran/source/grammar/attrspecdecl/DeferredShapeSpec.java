package com.puresol.coding.lang.fortran.source.grammar.attrspecdecl;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R520 deferred-shape-spec is :
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DeferredShapeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Colon.class);
		finish();
	}
}
