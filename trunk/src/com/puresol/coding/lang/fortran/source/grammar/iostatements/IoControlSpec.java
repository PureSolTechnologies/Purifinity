package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.NamelistGroupName;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ScalarIntVariable;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ScalarDefaultCharExpr;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ScalarIntExpr;
import com.puresol.coding.lang.fortran.source.grammar.lexical.Label;
import com.puresol.coding.lang.fortran.source.keywords.AdvanceKeyword;
import com.puresol.coding.lang.fortran.source.keywords.AsynchronousKeyword;
import com.puresol.coding.lang.fortran.source.keywords.BlankKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DecimalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DelimKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EorKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ErrKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FmtKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IdKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoMsgKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoStatKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NmlKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PadKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PosKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RecKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RoundKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SignKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SizeKeyword;
import com.puresol.coding.lang.fortran.source.keywords.UnitKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R913 io-control-spec is [ UNIT = ] io-unit
 * or [ FMT = ] format
 * or [ NML = ] namelist-group-name
 * or ADVANCE = scalar-default-char-expr
 * or ASYNCHRONOUS = scalar-default-char-constant-expr
 * or BLANK = scalar-default-char-expr
 * or DECIMAL = scalar-default-char-expr
 * or DELIM = scalar-default-char-expr
 * or END = label
 * or EOR = label
 * or ERR = label
 * or ID = id-variable
 * or IOMSG = iomsg-variable
 * or IOSTAT = scalar-int-variable
 * or PAD = scalar-default-char-expr
 * or POS = scalar-int-expr
 * or REC = scalar-int-expr
 * or ROUND = scalar-default-char-expr
 * or SIGN = scalar-default-char-expr
 * or SIZE = scalar-int-variable
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IoControlSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(UnitKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(FileUnitNumber.class);
		} else if (acceptToken(FmtKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Format.class);
		} else if (acceptToken(NmlKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(NamelistGroupName.class);
		} else if (acceptToken(AdvanceKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(AsynchronousKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(BlankKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(DecimalKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(DelimKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(EndKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Label.class);
		} else if (acceptToken(EorKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Label.class);
		} else if (acceptToken(ErrKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Label.class);
		} else if (acceptToken(IdKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(IdVariable.class);
		} else if (acceptToken(IoMsgKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(IoMsgVariable.class);
		} else if (acceptToken(IoStatKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(PadKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(PosKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntExpr.class);
		} else if (acceptToken(RecKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntExpr.class);
		} else if (acceptToken(RoundKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(SignKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(SizeKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else {
			if (acceptPart(IoUnit.class) != null) {
			} else if (acceptPart(Format.class) != null) {
			} else if (acceptPart(NamelistGroupName.class) != null) {

			} else {
				abort();
			}
		}
		finish();
	}

}
