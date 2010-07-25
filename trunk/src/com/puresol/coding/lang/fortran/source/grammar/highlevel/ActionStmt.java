package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.AllocateStmt;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.DeallocateStmt;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.NullifyStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.ArithmeticIfStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.ComputedGotoStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.ContinueStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.CycleStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.ErrorStopStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.ExitStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.GotoStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.IfStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.LockStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.StopStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.SyncAllStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.SyncImagesStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.SyncMemoryStmt;
import com.puresol.coding.lang.fortran.source.grammar.executioncontrol.UnlockStmt;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ForallStmt;
import com.puresol.coding.lang.fortran.source.grammar.expressions.PointerAssignmentStmt;
import com.puresol.coding.lang.fortran.source.grammar.expressions.WhereStmt;
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
		} else if (acceptPart(EndMpSubprogramStmt.class) != null) {
		} else if (acceptPart(EndProgramStmt.class) != null) {
		} else if (acceptPart(EndSubroutineStmt.class) != null) {
		} else if (acceptPart(EndfileStmt.class) != null) {
		} else if (acceptPart(ErrorStopStmt.class) != null) {
		} else if (acceptPart(ExitStmt.class) != null) {
		} else if (acceptPart(FlushStmt.class) != null) {
		} else if (acceptPart(ForallStmt.class) != null) {
		} else if (acceptPart(GotoStmt.class) != null) {
		} else if (acceptPart(IfStmt.class) != null) {
		} else if (acceptPart(InquireStmt.class) != null) {
		} else if (acceptPart(LockStmt.class) != null) {
		} else if (acceptPart(NullifyStmt.class) != null) {
		} else if (acceptPart(OpenStmt.class) != null) {
		} else if (acceptPart(PointerAssignmentStmt.class) != null) {
		} else if (acceptPart(PrintStmt.class) != null) {
		} else if (acceptPart(ReadStmt.class) != null) {
		} else if (acceptPart(ReturnStmt.class) != null) {
		} else if (acceptPart(RewindStmt.class) != null) {
		} else if (acceptPart(StopStmt.class) != null) {
		} else if (acceptPart(SyncAllStmt.class) != null) {
		} else if (acceptPart(SyncImagesStmt.class) != null) {
		} else if (acceptPart(SyncMemoryStmt.class) != null) {
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
