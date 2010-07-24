package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R530 bind-entity is entity-name
 * or / common-block-name /
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BindEntity extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(NameLiteral.class) != null) {
		} else if (acceptToken(Slash.class) != null) {
			expectToken(NameLiteral.class);
			expectToken(Slash.class);
		} else {
			abort();
		}
		finish();
	}

}
