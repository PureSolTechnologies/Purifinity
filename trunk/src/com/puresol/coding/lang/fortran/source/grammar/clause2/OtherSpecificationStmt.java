package com.puresol.coding.lang.fortran.source.grammar.clause2;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.AccessStmt;
import com.puresol.coding.lang.fortran.source.grammar.attributes.AllocatableStmt;
import com.puresol.coding.lang.fortran.source.grammar.attributes.AsynchronousStmt;
import com.puresol.coding.lang.fortran.source.grammar.attributes.BindStmt;
import com.puresol.coding.lang.fortran.source.grammar.attributes.CodimensionStmt;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R212 other-specification-stmt is access-stmt
 * or allocatable-stmt
 * or asynchronous-stmt
 * or bind-stmt
 * or codimension-stmt
 * or common-stmt
 * or data-stmt
 * or dimension-stmt
 * or equivalence-stmt
 * or external-stmt
 * or intent-stmt
 * or intrinsic-stmt
 * or namelist-stmt
 * or optional-stmt
 * or pointer-stmt
 * or protected-stmt
 * or save-stmt
 * or target-stmt
 * or volatile-stmt
 * or value-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class OtherSpecificationStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AccessStmt.class) != null) {
		} else if (acceptPart(AllocatableStmt.class) != null) {
		} else if (acceptPart(AsynchronousStmt.class) != null) {
		} else if (acceptPart(BindStmt.class) != null) {
		} else if (acceptPart(CodimensionStmt.class) != null) {
		} else if (acceptPart(CommonStmt.class) != null) {
		} else if (acceptPart(DataStmt.class) != null) {
		} else if (acceptPart(DimensionStmt.class) != null) {
		} else if (acceptPart(EquivalenceStmt.class) != null) {
		} else if (acceptPart(ExternalStmt.class) != null) {
		} else if (acceptPart(IntentStmt.class) != null) {
		} else if (acceptPart(IntrinsicStmt.class) != null) {
		} else if (acceptPart(NamelistStmt.class) != null) {
		} else if (acceptPart(OptionalStmt.class) != null) {
		} else if (acceptPart(PointerStmt.class) != null) {
		} else if (acceptPart(ProtectedStmt.class) != null) {
		} else if (acceptPart(SaveStmt.class) != null) {
		} else if (acceptPart(TargetStmt.class) != null) {
		} else if (acceptPart(VolatileStmt.class) != null) {
		} else if (acceptPart(ValueStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
