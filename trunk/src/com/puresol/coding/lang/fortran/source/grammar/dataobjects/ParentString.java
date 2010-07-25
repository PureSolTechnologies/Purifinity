package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.lexical.ScalarConstant;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R609 parent-string is scalar-variable-name
 * or array-element
 * or coindexed-named-object
 * or scalar-structure-component
 * or scalar-constant
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParentString extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ScalarVariableName.class) != null) {
		} else if (acceptPart(ArrayElement.class) != null) {
		} else if (acceptPart(CoindexedNamedObject.class) != null) {
		} else if (acceptPart(ScalarStrutureComponent.class) != null) {
		} else if (acceptPart(ScalarConstant.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
