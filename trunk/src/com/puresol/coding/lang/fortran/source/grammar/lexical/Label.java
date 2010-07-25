package com.puresol.coding.lang.fortran.source.grammar.lexical;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R312 label is digit [ digit [ digit [ digit [ digit ] ] ] ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Label extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		try {
			String text = getCurrentToken().getText();
			expectToken(IntegerLiteral.class);
			int i = Integer.valueOf(text);
			if ((i <= 0) || (i > 99999)) {
				abort();
			}
			finish();
		} catch (EndOfTokenStreamException e) {
			abort();
		}
	}
}
