package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.CommonKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R568 common-stmt is COMMON [ / [ common-block-name ] / ] common-block-object-list [ [ , ] / [ common-block-name ] / common-block-object-list ] ...
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CommonStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(CommonKeyword.class);
		if (acceptToken(Slash.class) != null) {
			acceptToken(NameLiteral.class);
			expectToken(Slash.class);
		}
		expectPart(CommonBlockObjectList.class);
		while (true) {
			if (acceptToken(Comma.class) != null) {
				expectToken(Slash.class);
				expectToken(NameLiteral.class);
				expectToken(Slash.class);
				expectPart(CommonBlockObjectList.class);
			} else if (acceptToken(Slash.class) != null) {
				expectToken(NameLiteral.class);
				expectToken(Slash.class);
				expectPart(CommonBlockObjectList.class);
			} else {
				break;
			}
		}
		finish();
	}
}
