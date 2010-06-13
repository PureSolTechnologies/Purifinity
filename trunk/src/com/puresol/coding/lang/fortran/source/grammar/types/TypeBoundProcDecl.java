package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R449 type-bound-proc-decl is binding-name [ => procedure-name ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeBoundProcDecl extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(NameLiteral.class);
		if (acceptToken(Equals.class) != null) {
			expectToken(GreaterThan.class);
			expectToken(NameLiteral.class);
		}
		finish();
	}

}
