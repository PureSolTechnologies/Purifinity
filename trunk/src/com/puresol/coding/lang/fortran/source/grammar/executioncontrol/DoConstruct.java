package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R813 do-construct is block-do-construct
 * or nonblock-do-construct
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DoConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(BlockDoConstruct.class) != null) {
		} else if (acceptPart(BlockDoConstruct.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
