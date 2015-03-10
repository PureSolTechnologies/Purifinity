package com.puresoltechnologies.purifinity.server.plugin.java7.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.server.metrics.spi.LanguageDependedCodeDepthMetric;

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
	public boolean isCascading(UniversalSyntaxTree node) {
		return blockNames.contains(node.getName());
	}

}
