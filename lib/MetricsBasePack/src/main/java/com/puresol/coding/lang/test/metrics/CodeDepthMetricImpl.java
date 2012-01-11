package com.puresol.coding.lang.test.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.metrics.codedepth.LanguageDependedCodeDepthMetric;
import com.puresol.uhura.parser.ParserTree;

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
	public boolean cascadingNode(ParserTree node) {
		return blockNames.contains(node.getName());
	}

}
