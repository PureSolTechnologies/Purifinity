package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.PrintKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R912 print-stmt is PRINT format [ , output-item-list ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrintStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(PrintKeyword.class);
		expectPart(Format.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(OutputItemList.class);
		}
		finish();
	}

}
