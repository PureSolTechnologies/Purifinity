package com.puresol.coding.lang.java.metrics;

import com.puresol.coding.metrics.codedepth.LanguageDependedCodeDepthMetric;
import com.puresol.uhura.ast.ParserTree;

/**
 * This is the actual implementation of the code block test for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeDepthMetricImpl implements LanguageDependedCodeDepthMetric {

	@Override
	public boolean cascadingNode(ParserTree node) {
		return "Block".equals(node.getName());
	}

}
