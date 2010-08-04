package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.programmunits.BlockData;
import com.puresol.coding.lang.fortran.source.grammar.programmunits.MainProgram;
import com.puresol.coding.lang.fortran.source.grammar.programmunits.Module;
import com.puresol.coding.lang.fortran.source.grammar.programmunits.Submodule;
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
		return CodeRangeType.PROGRAM;
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
