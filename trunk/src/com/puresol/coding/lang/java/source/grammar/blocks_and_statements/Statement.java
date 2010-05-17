package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * statement 
 *     :   block
 *             
 *     |   ('assert'
 *         )
 *         expression (':' expression)? ';'
 *     |   'assert'  expression (':' expression)? ';'            
 *     |   'if' parExpression statement ('else' statement)?          
 *     |   forstatement
 *     |   'while' parExpression statement
 *     |   'do' statement 'while' parExpression ';'
 *     |   trystatement
 *     |   'switch' parExpression '{' switchBlockStatementGroups '}'
 *     |   'synchronized' parExpression block
 *     |   'return' (expression )? ';'
 *     |   'throw' expression ';'
 *     |   'break'
 *             (IDENTIFIER
 *             )? ';'
 *     |   'continue'
 *             (IDENTIFIER
 *             )? ';'
 *     |   expression  ';'     
 *     |   IDENTIFIER ':' statement
 *     |   ';'
 * 
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Statement extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Block.class) != null) {
		} else if (acceptPart(AssertStatement.class) != null) {
		} else if (acceptPart(IfThenElseStatement.class) != null) {
		} else if (acceptPart(ForStatement.class) != null) {
		} else if (acceptPart(WhileStatement.class) != null) {
		} else if (acceptPart(DoStatement.class) != null) {
		} else if (acceptPart(TryStatement.class) != null) {
		} else if (acceptPart(SwitchStatement.class) != null) {
		} else if (acceptPart(SynchronizedStatement.class) != null) {
		} else if (acceptPart(ReturnStatement.class) != null) {
		} else if (acceptPart(ThrowStatement.class) != null) {
		} else if (acceptPart(BreakStatement.class) != null) {
		} else if (acceptPart(ContinueStatement.class) != null) {
		} else if (acceptPart(ExpressionStatement.class) != null) {
		} else if (acceptPart(LabeledStatement.class) != null) {
		} else if (acceptPart(EmptyStatement.class) != null) {
		} else {
			abort();
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
