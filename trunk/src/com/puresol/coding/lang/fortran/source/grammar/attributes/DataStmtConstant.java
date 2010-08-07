package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.lexical.ScalarConstant;
import com.puresol.coding.lang.fortran.source.grammar.types.InitialDataTarget;
import com.puresol.coding.lang.fortran.source.grammar.types.SignedIntLiteralConstant;
import com.puresol.coding.lang.fortran.source.grammar.types.SignedRealLiteralConstant;
import com.puresol.coding.lang.fortran.source.grammar.types.StructureConstructor;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R542 data-stmt-constant is scalar-constant
 * or scalar-constant-subobject
 * or signed-int-literal-constant
 * or signed-real-literal-constant
 * or null-init
 * or initial-data-target
 * or structure-constructor
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataStmtConstant extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ScalarConstant.class) != null) {
		} else if (acceptPart(ScalarConstantSubobject.class) != null) {
		} else if (acceptPart(SignedIntLiteralConstant.class) != null) {
		} else if (acceptPart(SignedRealLiteralConstant.class) != null) {
		} else if (acceptPart(NullInit.class) != null) {
		} else if (acceptPart(InitialDataTarget.class) != null) {
		} else if (acceptPart(StructureConstructor.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
