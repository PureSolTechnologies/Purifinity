package com.puresoltechnologies.purifinity.server.plugin.java7.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.purifinity.evaluation.api.LanguageDependedMcCabeMetric;

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
	public int increasesCyclomaticComplexityBy(AbstractProduction production) {
		if (!blockNames.contains(production.getName())) {
			return 0;
		}
		if ("SwitchLabel".equals(production.getName())) {
			if (production.getParent().getChildren().indexOf(production) > 0) {
				return 0;
			}
		}
		if ("QUESTION_MARK".equals(production.getName())) {
			if ("ConditionalExpression"
					.equals(production.getParent().getName())) {
				return 1;
			} else {
				return 0;
			}
		}
		return 1;
	}
}
