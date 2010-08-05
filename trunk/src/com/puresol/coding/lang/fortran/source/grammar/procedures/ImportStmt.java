package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ImportKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1209 import-stmt is IMPORT [[ :: ] import-name-list ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ImportStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ImportKeyword.class);
		if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
			expectPart(ImportNameList.class);
		} else {
			acceptPart(ImportNameList.class);
		}
		finish();
	}

}
