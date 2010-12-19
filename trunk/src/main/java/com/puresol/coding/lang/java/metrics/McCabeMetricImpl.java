package com.puresol.coding.lang.java.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.metrics.mccabe.LanguageDependedMcCabeMetric;
import com.puresol.uhura.ast.ParserTree;

/**
 * This is the actual implementation of the McCabe metric for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetricImpl implements LanguageDependedMcCabeMetric {

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
	}

	@Override
	public boolean increasesCyclomaticComplexity(ParserTree node) {
		return blockNames.contains(node.getName());
	}

}
