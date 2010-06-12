package com.puresol.coding.lang.fortran.source.grammar.types.derivedtypes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ProcedureKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R448 type-bound-procedure-stmt is PROCEDURE [ [ , binding-attr -list ] :: ] type-bound-proc-decl -list
 * or PROCEDURE (interface-name), binding-attr -list :: binding-name-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeBoundProcedureStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ProcedureKeyword.class);
		if (acceptToken(LParen.class) != null) {
			expectToken(NameLiteral.class);
			expectToken(RParen.class);
			expectToken(Comma.class);
			expectPart(BindingAttrList.class);
			expectToken(Colon.class);
			expectToken(Colon.class);
			expectPart(BindingNameList.class);
		} else {
			if (acceptToken(Comma.class) != null) {
				expectPart(BindingAttrList.class);
				expectToken(Colon.class);
				expectToken(Colon.class);
			} else if (acceptToken(Colon.class) != null) {
				expectToken(Colon.class);
			}
			expectPart(TypeBoundProcDeclList.class);
		}
		finish();
	}
}
