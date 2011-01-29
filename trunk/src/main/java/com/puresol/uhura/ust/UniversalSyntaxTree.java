package com.puresol.uhura.ust;

import com.puresol.trees.Tree;

public interface UniversalSyntaxTree extends Tree<UniversalSyntaxTree> {

	/**
	 * This method returns a symbol for the construction which is represented.
	 * The symbol is taken from the original language and can be used for
	 * Halstead metrics for instance.
	 * 
	 * As an example lets show for Java:
	 * 
	 * <pre>
	 * ForAll:	for(:)
	 * For:		for(;;)
	 * Add:		+
	 * </pre>
	 * 
	 * And so forth...
	 */
	public String getOriginalLanguageSymbol();

}
