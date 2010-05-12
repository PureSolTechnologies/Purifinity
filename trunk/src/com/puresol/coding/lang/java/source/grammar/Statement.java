package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.AssertKeyword;
import com.puresol.coding.lang.java.source.keywords.BreakKeyword;
import com.puresol.coding.lang.java.source.keywords.ContinueKeyword;
import com.puresol.coding.lang.java.source.keywords.DoKeyword;
import com.puresol.coding.lang.java.source.keywords.ElseKeyword;
import com.puresol.coding.lang.java.source.keywords.FinallyKeyword;
import com.puresol.coding.lang.java.source.keywords.ForKeyword;
import com.puresol.coding.lang.java.source.keywords.IfKeyword;
import com.puresol.coding.lang.java.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.java.source.keywords.SwitchKeyword;
import com.puresol.coding.lang.java.source.keywords.SynchronizedKeyword;
import com.puresol.coding.lang.java.source.keywords.ThrowKeyword;
import com.puresol.coding.lang.java.source.keywords.TryKeyword;
import com.puresol.coding.lang.java.source.keywords.WhileKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Statement extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(Block.class) != null) {
	} else if (acceptToken(AssertKeyword.class) != null) {
	    expectPart(Expression.class);
	    if (acceptToken(Colon.class) != null) {
		expectPart(Expression.class);
	    }
	    expectToken(Semicolon.class);
	} else if (acceptToken(IfKeyword.class) != null) {
	    expectPart(ParExpression.class);
	    expectPart(Statement.class);
	    if (acceptToken(ElseKeyword.class) != null) {
		expectPart(Statement.class);
	    }
	} else if (acceptToken(ForKeyword.class) != null) {
	    expectToken(LParen.class);
	    expectPart(ForControl.class);
	    expectToken(RParen.class);
	    expectPart(Statement.class);
	} else if (acceptToken(WhileKeyword.class) != null) {
	    expectPart(ParExpression.class);
	    expectPart(Statement.class);
	} else if (acceptToken(DoKeyword.class) != null) {
	    expectPart(Statement.class);
	    expectToken(WhileKeyword.class);
	    expectPart(ParExpression.class);
	    expectToken(Semicolon.class);
	} else if (acceptToken(TryKeyword.class) != null) {
	    expectPart(Block.class);
	    if (acceptPart(Catches.class) != null) {
		if (acceptToken(FinallyKeyword.class) != null) {
		    expectPart(Block.class);
		}
	    } else {
		expectToken(FinallyKeyword.class);
		expectPart(Block.class);
	    }
	} else if (acceptToken(SwitchKeyword.class) != null) {
	    expectPart(ParExpression.class);
	    expectToken(LCurlyBracket.class);
	    expectPart(SwitchBlockStatementGroups.class);
	    expectToken(RCurlyBracket.class);
	} else if (acceptToken(SynchronizedKeyword.class) != null) {
	    expectPart(ParExpression.class);
	    expectPart(Block.class);
	} else if (acceptToken(ReturnKeyword.class) != null) {
	    acceptPart(Expression.class);
	    expectToken(Semicolon.class);
	} else if (acceptToken(ThrowKeyword.class) != null) {
	    expectPart(Expression.class);
	    expectToken(Semicolon.class);
	} else if (acceptToken(BreakKeyword.class) != null) {
	    acceptToken(Identifier.class);
	} else if (acceptToken(ContinueKeyword.class) != null) {
	    acceptToken(Identifier.class);
	} else if (acceptToken(Semicolon.class) != null) {
	} else if (acceptToken(StatementExpression.class) != null) {
	    expectToken(Semicolon.class);
	} else {
	    expectToken(Identifier.class);
	    expectToken(Colon.class);
	    expectPart(Statement.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
