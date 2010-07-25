package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ScalarIntVariable;
import com.puresol.coding.lang.fortran.source.grammar.expressions.DefaultCharExpr;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ScalarDefaultCharExpr;
import com.puresol.coding.lang.fortran.source.grammar.lexical.Label;
import com.puresol.coding.lang.fortran.source.keywords.AccessKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ActionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.AsynchronousKeyword;
import com.puresol.coding.lang.fortran.source.keywords.BlankKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DecimalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DelimKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EncodingKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ErrKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FileKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FormKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoMsgKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoStatKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NewUnitKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PadKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PositionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReclKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RoundKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SignKeyword;
import com.puresol.coding.lang.fortran.source.keywords.StatusKeyword;
import com.puresol.coding.lang.fortran.source.keywords.UnitKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R905 connect-spec is [ UNIT = ] file-unit-number
 * or ACCESS = scalar-default-char-expr
 * or ACTION = scalar-default-char-expr
 * or ASYNCHRONOUS = scalar-default-char-expr
 * or BLANK = scalar-default-char-expr
 * or DECIMAL = scalar-default-char-expr
 * or DELIM = scalar-default-char-expr
 * or ENCODING = scalar-default-char-expr
 * or ERR = label
 * or FILE = le-name-expr
 * or FORM = scalar-default-char-expr
 * or IOMSG = iomsg-variable
 * or IOSTAT = scalar-int-variable
 * or NEWUNIT = scalar-int-variable
 * or PAD = scalar-default-char-expr
 * or POSITION = scalar-default-char-expr
 * or RECL = scalar-int-expr
 * or ROUND = scalar-default-char-expr
 * or SIGN = scalar-default-char-expr
 * or STATUS = scalar-default-char-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ConnectSpec extends AbstractFortranParser {

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
		} else if (acceptToken(AccessKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(ActionKeyword.class) != null) {
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
		} else if (acceptToken(EncodingKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(ErrKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Label.class);
		} else if (acceptToken(FileKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(FileNameExpr.class);
		} else if (acceptToken(FormKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(IoMsgKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(IoMsgVariable.class);
		} else if (acceptToken(IoStatKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(NewUnitKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(PadKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(PositionKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(ReclKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(DefaultCharExpr.class);
		} else if (acceptToken(RoundKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(SignKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else if (acceptToken(StatusKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharExpr.class);
		} else {
			expectPart(FileUnitNumber.class);
		}
		finish();
	}

}
