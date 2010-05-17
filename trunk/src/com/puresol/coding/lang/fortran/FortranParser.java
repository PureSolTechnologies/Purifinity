package com.puresol.coding.lang.fortran;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.parts.Function;
import com.puresol.coding.lang.fortran.source.parts.Module;
import com.puresol.coding.lang.fortran.source.parts.Program;
import com.puresol.coding.lang.fortran.source.parts.Subroutine;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class FortranParser extends AbstractFortranParser {

	private static final long serialVersionUID = 5137487026150640755L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		moveToNextProcessibleToken(0);
		if (acceptPart(Program.class) != null) {
		} else if (acceptPart(Subroutine.class) != null) {
		} else if (acceptPart(Function.class) != null) {
		} else if (acceptPart(Module.class) != null) {
		} else {
			throw new PartDoesNotMatchException(this);
		}
		finish(getFile().getName());
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FILE;
	}
}
