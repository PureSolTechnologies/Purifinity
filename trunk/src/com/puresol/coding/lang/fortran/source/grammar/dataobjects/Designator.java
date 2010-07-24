package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R601 designator is object-name
 * or array-element
 * or array-section
 * or coindexed-named-object
 * or complex-part-designator
 * or structure-component
 * or substring
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Designator extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(NameLiteral.class) != null) {
		} else if (acceptPart(ArrayElement.class) != null) {
		} else if (acceptPart(ArraySection.class) != null) {
		} else if (acceptPart(CoindexedNamedObject.class) != null) {
		} else if (acceptPart(ComplexPartDesignator.class) != null) {
		} else if (acceptPart(StructureComponent.class) != null) {
		} else if (acceptPart(Substring.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
