package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.ProcPointerName;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R738 proc-pointer-object is proc-pointer-name
 * or proc-component-ref
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcPointerObject extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ProcPointerName.class) != null) {
		} else if (acceptPart(ProcComponentRef.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
