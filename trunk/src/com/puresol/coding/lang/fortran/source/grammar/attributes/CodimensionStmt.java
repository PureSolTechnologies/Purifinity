package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.CodimensionKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R531 codimension-stmt is CODIMENSION [ :: ] codimension-decl -list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodimensionStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(CodimensionKeyword.class);
		if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
		}
		expectPart(CodimensionDeclList.class);
		finish();
	}

}
