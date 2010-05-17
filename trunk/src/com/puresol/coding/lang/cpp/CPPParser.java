package com.puresol.coding.lang.cpp;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.parser.PartDoesNotMatchException;

public class CPPParser extends AbstractCPPParser {

	private static final long serialVersionUID = -6109582097544650788L;

	@Override
	public void scan() throws PartDoesNotMatchException {
		moveToNextProcessibleToken(0);
		// TODO
		finish(getFile().getName());
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FILE;
	}
}
