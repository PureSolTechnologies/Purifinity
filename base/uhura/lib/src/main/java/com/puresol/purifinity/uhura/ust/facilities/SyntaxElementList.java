package com.puresol.purifinity.uhura.ust.facilities;

import java.util.List;

import com.puresol.purifinity.uhura.ust.AbstractUniversalSyntaxTree;
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
public class SyntaxElementList<T extends AbstractUniversalSyntaxTree> extends
		AbstractUniversalSyntaxTree {

	private static final long serialVersionUID = -1875399758682948173L;

	/**
	 * This constructor is inherited from AbstractUniversalSyntaxTree.
	 * 
	 * @param parent
	 * @param originalSymbol
	 *            could here be ',', ';' or something similar regarding the
	 *            source programming language.
	 */
	public SyntaxElementList(String originalSymbol,
			List<UniversalSyntaxTree> elements) {
		super(originalSymbol);
		addChildren(elements);
	}

	@Override
	public String getName() {
		return "Syntax Element List";
	}
}
