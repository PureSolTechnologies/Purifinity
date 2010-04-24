package com.puresol.coding.lang.cpp;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.PartDoesNotMatchException;

public class CPPParser extends AbstractCPPParser {

	private static final long serialVersionUID = -6109582097544650788L;

	@Override
	public void scan() throws PartDoesNotMatchException {
		try {
			moveToNextVisible(0);
			// TODO
		} catch (EndOfTokenStreamException e) {
			// this may happen if there is an empty file...
			return;
		}
		finish(getFile().getName());
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FILE;
	}
}
