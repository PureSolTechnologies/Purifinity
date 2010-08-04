package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.IntegerKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R431 type-param-def-stmt is INTEGER [ kind-selector ] , type-param-attr-spec ::
 * type-param-decl-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeParamDefStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(IntegerKeyword.class);
		acceptPart(KindSelector.class);
		expectToken(Comma.class);
		expectPart(TypeParamAttrSpec.class);
		expectToken(Colon.class);
		expectToken(Colon.class);
		expectPart(TypeParamDeclList.class);
		finish();
	}

}
