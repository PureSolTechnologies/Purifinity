package com.puresol.coding.lang.java.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.metrics.mccabe.LanguageDependedMcCabeMetric;
import com.puresol.uhura.parser.ParserTree;

/**
 * This is the actual implementation of the McCabe metric for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetricImpl implements LanguageDependedMcCabeMetric {

	private static final long serialVersionUID = 8745174421258035666L;

	private static final List<String> blockNames = new ArrayList<String>();
	static {
		blockNames.add("IfThenStatement");
		blockNames.add("IfThenElseStatement");
		blockNames.add("IfThenElseStatementNoShortIf");
		blockNames.add("BasicForStatement");
		blockNames.add("ForStatementNoShortIf");
		blockNames.add("EnhancedForStatement");
		blockNames.add("WhileStatement");
		blockNames.add("WhileStatementNoShortIf");
		blockNames.add("DoStatement");
		blockNames.add("CatchClause");
		blockNames.add("SwitchLabel");
		blockNames.add("QUESTION_MARK");
	}

	@Override
	public int increasesCyclomaticComplexityBy(ParserTree node) {
		if (!blockNames.contains(node.getName())) {
			return 0;
		}
		if ("SwitchLabel".equals(node.getName())) {
			if (node.getParent().getChildren().indexOf(node) > 0) {
				return 0;
			}
		}
		if ("QUESTION_MARK".equals(node.getName())) {
			if ("ConditionalExpression".equals(node.getParent().getName())) {
				return 1;
			} else {
				return 0;
			}
		}
		return 1;
	}
}
