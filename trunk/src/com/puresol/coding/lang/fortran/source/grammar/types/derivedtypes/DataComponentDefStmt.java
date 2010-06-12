package com.puresol.coding.lang.fortran.source.grammar.types.derivedtypes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.KindKeyword;
import com.puresol.coding.lang.fortran.source.keywords.LenKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R436 data-component-def-stmt is declaration-type-spec [ [ , component-attr-spec-list ] :: ]
 * component-decl-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataComponentDefStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(DeclarationTypeSpec.class);
		if (acceptToken(Comma.class) != null) {
			expectToken(ComponentAttrSpecList.class);
			expectToken(Colon.class);
			expectToken(Colon.class);
		} else if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
		}
		expectPart(ComponentDeclList.class);
		finish();
	}
}
