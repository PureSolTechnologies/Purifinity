package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.reporting.html.css.parser.symbols.Dot;

/**
 * <pre>
 * R713 rel-op is .EQ.
 * or ==
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EqualOp extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		try {
			if (acceptToken(Dot.class) != null) {
				String text;
				text = getCurrentToken().getText();
				expectToken(NameLiteral.class);
				if (!text.equalsIgnoreCase("EQ")) {
					abort();
				}
				expectToken(Dot.class);
			} else {
				expectToken(Equals.class);
				expectToken(Equals.class);
			}
			finish();
		} catch (EndOfTokenStreamException e) {
			abort();
		}
	}
}
