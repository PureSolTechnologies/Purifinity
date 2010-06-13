package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.NullInit;
import com.puresol.coding.lang.fortran.source.keywords.NoPassKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PassKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PointerKeyword;
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
 * R442 component-initialization is = constant-expr
 * or => null-init
 * or => initial-data-target
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ComponentInitialization extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Equals.class);
		if (acceptToken(GreaterThan.class) != null) {
			if (acceptPart(NullInit.class) != null) {
			} else if (acceptPart(InitialDataTarget.class) != null) {
			} else {
				abort();
			}
		} else {
			expectPart(ConstantExpr.class);
		}
		finish();
	}
}
