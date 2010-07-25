package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndFileKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R925 endfile-stmt is ENDFILE file-unit-number
 * or ENDFILE ( position-spec-list )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndfileStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(EndFileKeyword.class);
		if (acceptToken(LParen.class) != null) {
			expectPart(PositionSpecList.class);
			expectToken(RParen.class);
		} else {
			expectPart(FileUnitNumber.class);
		}
		finish();
	}

}
