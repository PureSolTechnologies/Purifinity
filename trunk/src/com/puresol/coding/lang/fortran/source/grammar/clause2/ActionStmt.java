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
 * R214 action-stmt is allocate-stmt
 * or assignment-stmt
 * or backspace-stmt
 * or call-stmt
 * or close-stmt
 * or continue-stmt
 * or cycle-stmt
 * or deallocate-stmt
 * or end-function-stmt
 * or end-mp-subprogram-stmt
 * or end-program-stmt
 * or end-subroutine-stmt
 * or endfile-stmt
 * or error-stop-stmt
 * or exit-stmt
 * or flush-stmt
 * or forall-stmt
 * or goto-stmt
 * or if-stmt
 * or inquire-stmt
 * or lock-stmt
 * or nullify-stmt
 * or open-stmt
 * or pointer-assignment-stmt
 * or print-stmt
 * or read-stmt
 * or return-stmt
 * or rewind-stmt
 * or stop-stmt
 * or sync-all-stmt
 * or sync-images-stmt
 * or sync-memory-stmt
 * or unlock-stmt
 * or wait-stmt
 * or where-stmt
 * or write-stmt
 * or arithmetic-if-stmt
 * or computed-goto-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ActionStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AllocateStmt.class) != null) {
		} else if (acceptPart(AssignmentStmt.class) != null) {
		} else if (acceptPart(BackspaceStmt.class) != null) {
		} else if (acceptPart(CallStmt.class) != null) {
		} else if (acceptPart(CloseStmt.class) != null) {
		} else if (acceptPart(ContinueStmt.class) != null) {
		} else if (acceptPart(CycleStmt.class) != null) {
		} else if (acceptPart(DeallocateStmt.class) != null) {
		} else if (acceptPart(EndFunctionStmt.class) != null) {
		} else if (acceptPart(EndMpSubprogram - stmt.class) != null) {
		} else if (acceptPart(EndProgram - stmt.class) != null) {
		} else if (acceptPart(EndSubroutine - stmt.class) != null) {
		} else if (acceptPart(EndfileStmt.class) != null) {
		} else if (acceptPart(ErrorStop - stmt.class) != null) {
		} else if (acceptPart(ExitStmt.class) != null) {
		} else if (acceptPart(FlushStmt.class) != null) {
		} else if (acceptPart(ForallStmt.class) != null) {
		} else if (acceptPart(GotoStmt.class) != null) {
		} else if (acceptPart(IfStmt.class) != null) {
		} else if (acceptPart(InquireStmt.class) != null) {
		} else if (acceptPart(LockStmt.class) != null) {
		} else if (acceptPart(NullifyStmt.class) != null) {
		} else if (acceptPart(OpenStmt.class) != null) {
		} else if (acceptPart(PointerAssignment - stmt.class) != null) {
		} else if (acceptPart(PrintStmt.class) != null) {
		} else if (acceptPart(ReadStmt.class) != null) {
		} else if (acceptPart(ReturnStmt.class) != null) {
		} else if (acceptPart(RewindStmt.class) != null) {
		} else if (acceptPart(StopStmt.class) != null) {
		} else if (acceptPart(SyncAllStmt.class) != null) {
		} else if (acceptPart(SyncImages - stmt.class) != null) {
		} else if (acceptPart(SyncMemory - stmt.class) != null) {
		} else if (acceptPart(UnlockStmt.class) != null) {
		} else if (acceptPart(WaitStmt.class) != null) {
		} else if (acceptPart(WhereStmt.class) != null) {
		} else if (acceptPart(WriteStmt.class) != null) {
		} else if (acceptPart(ArithmeticIfStmt.class) != null) {
		} else if (acceptPart(ComputedGotoStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
