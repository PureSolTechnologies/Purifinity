package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.DeclarationTypeSpec;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R501 type-declaration-stmt is declaration-type-spec [ [ , attr-spec ] ... :: ] entity-decl -list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeDeclarationStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(DeclarationTypeSpec.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(AttrSpec.class);
			while (acceptToken(Comma.class) != null) {
				expectPart(AttrSpec.class);
			}
			expectToken(Colon.class);
			expectToken(Colon.class);
		} else if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
		}
		expectPart(EntityDeclList.class);
		finish();
	}
}
