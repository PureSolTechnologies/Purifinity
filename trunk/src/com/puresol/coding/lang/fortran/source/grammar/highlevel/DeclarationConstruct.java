package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.ParameterStmt;
import com.puresol.coding.lang.fortran.source.grammar.attributes.TypeDeclarationStmt;
import com.puresol.coding.lang.fortran.source.grammar.types.DerivedTypeDef;
import com.puresol.coding.lang.fortran.source.grammar.types.derivedtypes.EnumDef;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R207 declaration-construct is derived-type-def
 * or entry-stmt
 * or enum-def
 * or format-stmt
 * or interface-block
 * or parameter-stmt
 * or procedure-declaration-stmt
 * or other-specication-stmt
 * or type-declaration-stmt
 * or stmt-function-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DeclarationConstruct extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DerivedTypeDef.class) != null) {
		} else if (acceptPart(EntryStmt.class) != null) {
		} else if (acceptPart(EnumDef.class) != null) {
		} else if (acceptPart(FormatStmt.class) != null) {
		} else if (acceptPart(InterfaceBlock.class) != null) {
		} else if (acceptPart(ParameterStmt.class) != null) {
		} else if (acceptPart(ProcedureDeclarationStmt.class) != null) {
		} else if (acceptPart(OtherSpecificationStmt.class) != null) {
		} else if (acceptPart(TypeDeclarationStmt.class) != null) {
		} else if (acceptPart(StmtFunctionStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
