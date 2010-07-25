package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ArrayElement;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.StructureComponent;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R538 data-i-do-object is array-element
 * or scalar-structure-component
 * or data-implied-do
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataIDoObject extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ArrayElement.class) != null) {
		} else if (acceptPart(StructureComponent.class) != null) {
		} else if (acceptPart(DataImpliedDo.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
