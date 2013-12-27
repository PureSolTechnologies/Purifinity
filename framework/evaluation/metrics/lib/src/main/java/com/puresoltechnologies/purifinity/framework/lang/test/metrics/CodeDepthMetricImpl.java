package com.puresoltechnologies.purifinity.framework.lang.test.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.LanguageDependedCodeDepthMetric;

/**
 * This is the actual implementation of the code block test for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeDepthMetricImpl implements LanguageDependedCodeDepthMetric {

	private static final long serialVersionUID = -5364624376125586289L;

	private static final List<String> blockNames = new ArrayList<String>();
	static {
		blockNames.add("Block");
		blockNames.add("ClassBody");
		blockNames.add("InterfaceBody");
		blockNames.add("EnumBody");
		blockNames.add("AnnotationTypeBody");
	}

	@Override
	public boolean cascadingNode(UniversalSyntaxTree node) {
		return blockNames.contains(node.getName());
	}

}