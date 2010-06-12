package com.puresol.coding.lang.fortran.source.grammar.types.derivedtypes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attrspecdecl.attributes.AccessSpec;
import com.puresol.coding.lang.fortran.source.keywords.GenericKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProcedureKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R450 type-bound-generic-stmt is GENERIC [ , access-spec ] :: generic-spec => binding-name-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeBoundGenericStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(GenericKeyword.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(AccessSpec.class);
		}
		expectToken(Colon.class);
		expectToken(Colon.class);
		expectPart(GenericSpec.class);
		expectToken(Equals.class);
		expectToken(GreaterThan.class);
		expectPart(BindingNameList.class);
		finish();
	}
}
