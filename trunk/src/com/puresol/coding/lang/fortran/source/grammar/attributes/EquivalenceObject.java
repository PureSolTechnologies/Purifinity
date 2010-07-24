package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ArrayElement;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.Substring;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R567 equivalence-object is variable-name
 * or array-element
 * or substring
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EquivalenceObject extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(NameLiteral.class) != null) {
		} else if (acceptPart(ArrayElement.class) != null) {
		} else if (acceptPart(Substring.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
