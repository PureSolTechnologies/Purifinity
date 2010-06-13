package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.TypeKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R426 derived-type-stmt is TYPE [ [ , type-attr-spec-list ] :: ] type-name
 * [ ( type-param-name-list ) ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DerivedTypeStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(TypeKeyword.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(TypeAttrSpecList.class);
			expectToken(Colon.class);
			expectToken(Colon.class);
		} else if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(NameLiteral.class);
		if (acceptToken(LParen.class) != null) {
			expectPart(TypeParamNameList.class);
			expectToken(RParen.class);
		}
		finish();
	}

}
