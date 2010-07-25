package com.puresol.coding.lang.fortran.source.grammar.expressions;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.AllocateStmt;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.DeallocateStmt;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.Designator;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.NullifyStmt;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.TypeParamInquiry;
import com.puresol.coding.lang.fortran.source.grammar.lexical.Constant;
import com.puresol.coding.lang.fortran.source.grammar.types.ArrayConstructor;
import com.puresol.coding.lang.fortran.source.grammar.types.StructureConstructor;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R701 primary is constant
 * or designator
 * or array-constructor
 * or structure-constructor
 * or function-reference
 * or type-param-inquiry
 * or type-param-name
 * or ( expr )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Primary extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Constant.class) != null) {
		} else if (acceptPart(Designator.class) != null) {
		} else if (acceptPart(ArrayConstructor.class) != null) {
		} else if (acceptPart(StructureConstructor.class) != null) {
		} else if (acceptPart(FunctionReference.class) != null) {
		} else if (acceptPart(TypeParamInquiry.class) != null) {
		} else if (acceptPart(TypeParamName.class) != null) {
		} else if (acceptToken(LParen.class) != null) {
			expectPart(Expr.class);
			expectToken(RParen.class);
		} else {
			abort();
		}
		finish();
	}
}
