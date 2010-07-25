package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.FlushKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R928 
 * ush-stmt is FLUSH file-unit-number
 * or FLUSH ( flush-spec-list )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FlushStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(FlushKeyword.class);
		if (acceptToken(LParen.class) != null) {
			expectPart(FlushSpecList.class);
			expectToken(RParen.class);
		} else {
			expectPart(FileUnitNumber.class);
		}
		finish();
	}

}
