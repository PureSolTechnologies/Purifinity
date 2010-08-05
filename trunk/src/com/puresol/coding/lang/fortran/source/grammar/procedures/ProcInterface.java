package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.DeclarationTypeSpec;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1212 proc-interface is interface-name
 * or declaration-type-spec
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcInterface extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(InterfaceName.class) != null) {
		} else if (acceptPart(DeclarationTypeSpec.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
