package com.puresol.purifinity.coding.lang.java7.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.coding.metrics.codedepth.LanguageDependedCodeDepthMetric;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

/**
 * This is the actual implementation of the code block test for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeDepthMetricImpl implements LanguageDependedCodeDepthMetric {

	private static final long serialVersionUID = -2076438005553819250L;

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
