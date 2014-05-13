package com.puresoltechnologies.purifinity.server.plugin.fortran2008.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.trees.TreeException;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.USTUtils;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.evaluation.api.LanguageDependedMcCabeMetric;

/**
 * This is the actual implementation of the McCabe metric for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetricImpl implements LanguageDependedMcCabeMetric {

	private static final long serialVersionUID = -821106855247622841L;

	private static final List<String> blockNames = new ArrayList<String>();
	static {
		blockNames.add("if-then-stmt");
		blockNames.add("else-if-stmt");
		blockNames.add("if-stmt");
		blockNames.add("arithmetic-if-stmt");
		blockNames.add("do-stmt");
		blockNames.add("case-stmt");
		blockNames.add("type-guard-stmt");
		blockNames.add("computed-goto-stmt");
	}

	@Override
	public int increasesCyclomaticComplexityBy(AbstractProduction production) {
		if (!blockNames.contains(production.getName())) {
			return 0;
		}
		if ("arithmetic-if-stmt".equals(production.getName())) {
			return 2;
		}
		if ("computed-goto-stmt".equals(production.getName())) {
			try {
				List<UniversalSyntaxTree> labels = USTUtils.getSubTrees(
						production.getChild("label-list"), "label");
				return labels.size() - 1;
			} catch (TreeException e) {
				return 0;
			}
		}
		return 1;
	}
}
