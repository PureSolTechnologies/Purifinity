package com.puresol.coding.lang.fortran.source.grammar.clause2;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R202 program-unit is main-program
 * or external-subprogram
 * or module
 * or submodule
 * or block-data
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgramUnit extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(MainProgram.class) != null) {
		} else if (acceptPart(ExternalSubprogram.class) != null) {
		} else if (acceptPart(Module.class) != null) {
		} else if (acceptPart(Submodule.class) != null) {
		} else if (acceptPart(BlockData.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
