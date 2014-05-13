package com.puresoltechnologies.purifinity.server.plugin.fortran2008.metrics;

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

	private static final long serialVersionUID = 3346220276003100548L;

	private static final List<String> blockNames = new ArrayList<String>();
	static {
		blockNames.add("main-program");
		blockNames.add("function-subprogram");
		blockNames.add("subroutine-subprogram");
		blockNames.add("separate-module-subprogram");
		blockNames.add("module");
		blockNames.add("submodule");
		blockNames.add("block-data");
		blockNames.add("derived-type-stmt");
		blockNames.add("enum-def");
		blockNames.add("where-construct");
		blockNames.add("masked-elsewhere-stmt");
		blockNames.add("elsewhere-stmt");
		blockNames.add("forall-construct");
		blockNames.add("associate-construct");
		blockNames.add("block-construct");
		blockNames.add("critical-construct");
		blockNames.add("block-do-construct");
		blockNames.add("nonblock-do-construct");
		blockNames.add("if-construct");
		blockNames.add("case-construct");
		blockNames.add("select-type-construct");
		blockNames.add("interface-block");
		blockNames.add("interface-specification");
	}

	@Override
	public boolean cascadingNode(UniversalSyntaxTree node) {
		return blockNames.contains(node.getName());
	}

}
