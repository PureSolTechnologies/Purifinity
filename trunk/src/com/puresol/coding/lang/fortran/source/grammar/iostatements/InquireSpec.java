package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ScalarDefaultCharVariable;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ScalarIntVariable;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ScalarLogicalVariable;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ScalarIntExpr;
import com.puresol.coding.lang.fortran.source.grammar.lexical.Label;
import com.puresol.coding.lang.fortran.source.keywords.AccessKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ActionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.AsynchronousKeyword;
import com.puresol.coding.lang.fortran.source.keywords.BlankKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DecimalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DelimKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DirectKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EncodingKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ErrKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ExistKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FileKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FormKeyword;
import com.puresol.coding.lang.fortran.source.keywords.FormattedKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IdKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoMsgKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoStatKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NameKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NamedKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NextRecKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NumberKeyword;
import com.puresol.coding.lang.fortran.source.keywords.OpenedKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PadKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PendingKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PosKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PositionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReadKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReadWriteKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReclKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RoundKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SequentialKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SignKeyword;
import com.puresol.coding.lang.fortran.source.keywords.SizeKeyword;
import com.puresol.coding.lang.fortran.source.keywords.StreamKeyword;
import com.puresol.coding.lang.fortran.source.keywords.UnformattedKeyword;
import com.puresol.coding.lang.fortran.source.keywords.UnitKeyword;
import com.puresol.coding.lang.fortran.source.keywords.WriteKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R931 inquire-spec is [ UNIT = ] file-unit-number
 * or FILE = file-name-expr
 * or ACCESS = scalar-default-char-variable
 * or ACTION = scalar-default-char-variable
 * or ASYNCHRONOUS = scalar-default-char-variable
 * or BLANK = scalar-default-char-variable
 * or DECIMAL = scalar-default-char-variable
 * or DELIM = scalar-default-char-variable
 * or DIRECT = scalar-default-char-variable
 * or ENCODING = scalar-default-char-variable
 * or ERR = label
 * or EXIST = scalar-logical-variable
 * or FORM = scalar-default-char-variable
 * or FORMATTED = scalar-default-char-variable
 * or ID = scalar-int-expr
 * or IOMSG = iomsg-variable
 * or IOSTAT = scalar-int-variable
 * or NAME = scalar-default-char-variable
 * or NAMED = scalar-logical-variable
 * or NEXTREC = scalar-int-variable
 * or NUMBER = scalar-int-variable
 * or OPENED = scalar-logical-variable
 * or PAD = scalar-default-char-variable
 * or PENDING = scalar-logical-variable
 * or POS = scalar-int-variable
 * or POSITION = scalar-default-char-variable
 * or READ = scalar-default-char-variable
 * or READWRITE = scalar-default-char-variable
 * or RECL = scalar-int-variable
 * or ROUND = scalar-default-char-variable
 * or SEQUENTIAL = scalar-default-char-variable
 * or SIGN = scalar-default-char-variable
 * or SIZE = scalar-int-variable
 * or STREAM = scalar-default-char-variable
 * or UNFORMATTED = scalar-default-char-variable
 * or WRITE = scalar-default-char-variable
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InquireSpec extends AbstractFortranParser {

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
		} else if (acceptToken(FileKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(FileNameExpr.class);
		} else if (acceptToken(AccessKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(ActionKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(AsynchronousKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(BlankKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(DecimalKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(DelimKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(DirectKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(EncodingKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(ErrKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(Label.class);
		} else if (acceptToken(ExistKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarLogicalVariable.class);
		} else if (acceptToken(FormKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(FormattedKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(IdKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntExpr.class);
		} else if (acceptToken(IoMsgKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(IoMsgVariable.class);
		} else if (acceptToken(IoStatKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(NameKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(NamedKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarLogicalVariable.class);
		} else if (acceptToken(NextRecKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(NumberKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(OpenedKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(PadKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(PendingKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarLogicalVariable.class);
		} else if (acceptToken(PosKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(PositionKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(ReadKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(ReadWriteKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(ReclKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(RoundKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(SequentialKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(SignKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(SizeKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		} else if (acceptToken(StreamKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(UnformattedKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		} else if (acceptToken(WriteKeyword.class) != null) {
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharVariable.class);
		}
		finish();
	}

}
