package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1002 format-specification is ( [ format-items ] )
 * or ( [ format-items, ] unlimited-format-item )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FormatSpecification extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LParen.class);
		if (acceptPart(FormatItems.class) != null) {
			if (acceptToken(Comma.class) != null) {
				expectPart(UnlimitedFormatItem.class);
			} else {
				expectPart(UnlimitedFormatItem.class);
			}
		}
		expectToken(RParen.class);
		finish();
	}
}
