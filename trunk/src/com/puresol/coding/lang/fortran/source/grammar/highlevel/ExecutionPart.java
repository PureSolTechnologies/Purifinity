package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R208 execution-part is executable-construct
 * [ execution-part-construct ] ...
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExecutionPart extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ExecutableConstruct.class);
		while (acceptPart(ExecutionPartConstruct.class) != null) {
		}
		finish();
	}

}
