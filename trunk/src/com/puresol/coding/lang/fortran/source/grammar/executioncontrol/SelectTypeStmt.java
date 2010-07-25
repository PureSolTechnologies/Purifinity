package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.SelectTypeKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R847 select-type-stmt is [ select-construct-name : ] SELECT TYPE ( [ associate-name => ] selector )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SelectTypeStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(SelectConstructName.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(SelectTypeKeyword.class);
		expectToken(LParen.class);
		if (acceptPart(AssociateName.class) != null) {
			expectToken(Equals.class);
			expectToken(GreaterThan.class);
		}
		expectPart(Selector.class);
		expectToken(RParen.class);
		finish();
	}
}
