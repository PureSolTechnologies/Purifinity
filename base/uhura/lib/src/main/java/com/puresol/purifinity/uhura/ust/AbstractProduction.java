package com.puresol.purifinity.uhura.ust;

import java.util.Arrays;
import java.util.List;

/**
 * This class is an implementation of a production. A production is a definition
 * for a group of productions and tokens. A production always has at least on
 * child.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractProduction extends
		AbstractUniversalSyntaxTreeNode {

	private static final long serialVersionUID = 1640932177605345931L;

	/**
	 * This method computes the node meta data for the node which contains the
	 * children specified. The node is constructed afterwards.
	 * 
	 * @param children
	 *            is a {@link List} of {@link UniversalSyntaxTree} object which
	 *            are meant to be assigned to a node.
	 * @return A {@link UniversalSyntaxTreeMetaData} object is returned which
	 *         can be assigned to a node as meta data.
	 */
	private static UniversalSyntaxTreeMetaData computeMetaData(
			List<UniversalSyntaxTree> children) {
		if ((children == null) || (children.size() == 0)) {
			throw new IllegalStateException(
					"A production needs to have at least one child.");
		}
		UniversalSyntaxTree firstChild = children.get(0);
		UniversalSyntaxTreeMetaData firstMetaData = firstChild.getMetaData();
		UniversalSyntaxTree lastChild = children.get(children.size() - 1);
		UniversalSyntaxTreeMetaData lastMetaData = lastChild.getMetaData();
		int line = firstMetaData.getLine();
		int lineNum = lastMetaData.getLine() - line + lastMetaData.getLineNum();
		int column = firstMetaData.getColumn();
		int length = 0;
		for (UniversalSyntaxTree child : children) {
			length += child.getMetaData().getLength();
		}
		return new UniversalSyntaxTreeMetaData(line, lineNum, column, length);
	}

	public AbstractProduction(String name, String originalSymbol,
			List<UniversalSyntaxTree> children) {
		super(name, originalSymbol, null, computeMetaData(children), children);
	}

	public AbstractProduction(String name, String originalSymbol,
			String content, List<UniversalSyntaxTree> children) {
		super(name, originalSymbol, content, computeMetaData(children),
				children);
	}

	public AbstractProduction(String name, String originalSymbol,
			UniversalSyntaxTree... children) {
		super(name, originalSymbol, null, computeMetaData(Arrays
				.asList(children)), Arrays.asList(children));
	}

	public AbstractProduction(String name, String originalSymbol,
			String content, UniversalSyntaxTree... children) {
		super(name, originalSymbol, content, computeMetaData(Arrays
				.asList(children)), Arrays.asList(children));
	}

	@Override
	public String toString() {
		if (getContent() == null) {
			return getName() + "(" + getOriginalName() + ")";
		} else {
			return getName() + "(" + getOriginalName() + "): '" + getContent()
					+ "'";
		}
	}

}
