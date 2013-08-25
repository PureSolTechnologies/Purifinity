package com.puresol.purifinity.uhura.ust.token;

import java.util.List;

import com.puresol.commons.utils.StringUtils;
import com.puresol.purifinity.uhura.grammar.token.Visibility;
import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTreeNode;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTreeMetaData;

/**
 * This is an abstract implementation of a token. A token is a part of source of
 * a compilation unit and contains at least on character. Tokens do not have
 * children.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AbstractToken extends AbstractUniversalSyntaxTreeNode {

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
					"A token needs to have at least one character.");
		}
		return content;
	}

	private final Visibility visibility;

	public AbstractToken(String name, String originalName, String content,
			int line, int column, Visibility visibility) {
		super(name, originalName, checkContent(content),
				new UniversalSyntaxTreeMetaData(line,
						StringUtils.countLineBreaks(content) + 1, column,
						content.length()));
		this.visibility = visibility;
	}

	public final Visibility getVisibility() {
		return visibility;
	}
}
