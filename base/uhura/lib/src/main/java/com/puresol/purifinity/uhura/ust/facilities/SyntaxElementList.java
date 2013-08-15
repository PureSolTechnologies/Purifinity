package com.puresol.purifinity.uhura.ust.facilities;

import java.util.List;

import com.puresol.purifinity.uhura.ust.USTNode;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

/**
 * This class represents a typed list like for parameter lists in method
 * declarations, initialized lists in Java-style for-loops or statement lists in
 * code blocks.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the type or interface of the elements contained in that list.
 */
public class SyntaxElementList<T extends USTNode> extends USTNode {

	private static final long serialVersionUID = -1875399758682948173L;

	/**
	 * This constructor is inherited from AbstractUSTNode.
	 * 
	 * @param parent
	 * @param originalSymbol
	 *            could here be ',', ';' or something similar regarding the
	 *            source programming language.
	 */
	public SyntaxElementList(String originalSymbol, List<USTNode> elements) {
		super("Syntax Element List", originalSymbol, elements
				.toArray(new UniversalSyntaxTree[elements.size()]));
	}
}
