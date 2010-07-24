package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R618 array-section is data-ref [ ( substring-range ) ]
 * or complex-part-designator
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ArraySection extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ComplexPartDesignator.class) != null) {
		} else {
			expectPart(DataRef.class);
			if (acceptToken(LParen.class) != null) {
				expectPart(SubstringRange.class);
				expectToken(RParen.class);
			}
		}
		finish();
	}
}
