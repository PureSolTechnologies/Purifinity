package com.puresoltechnologies.parsers.ust.terminal;

import com.puresoltechnologies.commons.types.StringUtils;
import com.puresoltechnologies.parsers.ust.AbstractUniversalSyntaxTreeNode;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTreeMetaData;

/**
 * This is an abstract implementation of a terminal. A terminal is a part of
 * source of a compilation unit and contains at least on character. Terminals do
 * not have children.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractTerminal extends AbstractUniversalSyntaxTreeNode {

	private static final long serialVersionUID = -1620557770406497800L;

	private final boolean visible;

	public AbstractTerminal(String name, String content, int line, int column,
			boolean visible) {
		super(name, content, new UniversalSyntaxTreeMetaData(line,
				StringUtils.countLineBreaks(content) + 1, column,
				content.length()));
		this.visible = visible;
	}

	public final boolean isVisible() {
		return visible;
	}
}
