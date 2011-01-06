package com.puresol.coding.lang.fortran.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.metrics.codedepth.LanguageDependedCodeDepthMetric;
import com.puresol.uhura.ast.ParserTree;

/**
 * This is the actual implementation of the code block test for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeDepthMetricImpl implements LanguageDependedCodeDepthMetric {

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
	public boolean cascadingNode(ParserTree node) {
		return blockNames.contains(node.getName());
	}

}
