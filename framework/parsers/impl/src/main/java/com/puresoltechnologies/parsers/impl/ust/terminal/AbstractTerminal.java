package com.puresoltechnologies.parsers.impl.ust.terminal;

import java.util.List;

import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTreeMetaData;
import com.puresoltechnologies.parsers.impl.ust.AbstractUniversalSyntaxTreeNode;
import com.puresoltechnologies.purifinity.framework.commons.utils.StringUtils;

/**
 * This is an abstract implementation of a terminal. A terminal is a part of
 * source of a compilation unit and contains at least on character. Terminals do
 * not have children.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AbstractTerminal extends AbstractUniversalSyntaxTreeNode {

	private static final long serialVersionUID = -1620557770406497800L;

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

	private static String checkContent(String content) {
		if ((content == null) || (content.length() == 0)) {
			throw new IllegalStateException(
					"A terminal needs to have at least one character.");
		}
		return content;
	}

	private final boolean visible;

	public AbstractTerminal(String name, String content, int line, int column,
			boolean visible) {
		super(name, checkContent(content), new UniversalSyntaxTreeMetaData(
				line, StringUtils.countLineBreaks(content) + 1, column,
				content.length()));
		this.visible = visible;
	}

	public final boolean isVisible() {
		return visible;
	}
}
