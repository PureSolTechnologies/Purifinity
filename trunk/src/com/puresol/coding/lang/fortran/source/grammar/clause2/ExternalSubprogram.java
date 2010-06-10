package com.puresol.coding.lang.fortran.source.grammar.clause2;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R203 external-subprogram is function-subprogram
 * or subroutine-subprogram
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExternalSubprogram extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(FunctionSubprogram.class) != null) {
		} else if (acceptPart(SubroutineSubprogram.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
