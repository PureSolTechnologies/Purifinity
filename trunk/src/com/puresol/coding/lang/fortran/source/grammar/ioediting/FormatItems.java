package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1003 format-items is format-item [ [ , ] format-item ] ...
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FormatItems extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(FormatItem.class);
		while (true) {
			if (acceptToken(Comma.class) != null) {
				expectPart(FormatItem.class);
			} else if (acceptPart(FormatItem.class) != null) {
			} else {
				break;
			}
		}
		finish();
	}
}
