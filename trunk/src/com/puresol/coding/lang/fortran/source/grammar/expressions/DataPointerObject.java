package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.VariableName;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.Percent;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.reporting.html.css.parser.symbols.Dot;

/**
 * <pre>
 * R734 data-pointer-object is variable-name
 * or scalar-variable % data-pointer-component-name
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataPointerObject extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(VariableName.class) != null) {
		} else if (acceptPart(ScalarVariable.class) != null) {
			expectToken(Percent.class);
			expectPart(DataPointerComponentName.class);
		} else {
			abort();
		}
		finish();
	}
}
