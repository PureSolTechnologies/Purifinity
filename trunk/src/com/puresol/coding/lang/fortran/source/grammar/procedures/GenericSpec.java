package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.lexical.DefinedOperator;
import com.puresol.coding.lang.fortran.source.keywords.AssignmentKeyword;
import com.puresol.coding.lang.fortran.source.keywords.OperatorKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1207 generic-spec is generic-name
 * or OPERATOR ( defined-operator )
 * or ASSIGNMENT ( = )
 * or defined-io-generic-spec
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GenericSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(NameLiteral.class) != null) {
		} else if (acceptToken(OperatorKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(DefinedOperator.class);
			expectToken(RParen.class);
		} else if (acceptToken(AssignmentKeyword.class) != null) {
			expectToken(LParen.class);
			expectToken(Equals.class);
			expectToken(RParen.class);
		} else if (acceptPart(DefinedIoGenericSpec.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
