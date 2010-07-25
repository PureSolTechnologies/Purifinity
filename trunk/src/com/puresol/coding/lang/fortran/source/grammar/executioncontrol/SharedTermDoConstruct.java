package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R828 shared-term-do-construct is outer-shared-do-construct
 * or inner-shared-do-construct
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SharedTermDoConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(OuterSharedDoConstruct.class) != null) {
		} else if (acceptPart(InnerSharedDoConstruct.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
