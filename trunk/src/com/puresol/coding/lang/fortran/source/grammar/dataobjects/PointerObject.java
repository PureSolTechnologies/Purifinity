package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.ProcPointerName;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R639 pointer-object is variable-name
 * or structure-component
 * or proc-pointer-name
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PointerObject extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(VariableName.class) != null) {
		} else if (acceptPart(StructureComponent.class) != null) {
		} else if (acceptPart(ProcPointerName.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
