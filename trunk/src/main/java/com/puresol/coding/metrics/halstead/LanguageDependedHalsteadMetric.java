package com.puresol.coding.metrics.halstead;

import java.io.Serializable;

import com.puresol.uhura.ast.ParserTree;

/**
 * This interface is used to implement a part of the Halstead metric
 * implementation within language packages. This implementation is used to
 * distinguish between operators and operands in languages.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface LanguageDependedHalsteadMetric extends Serializable {

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of token in context to the language.
	 * 
	 * @param node
	 *            is the node to be checked. The node is needed to check context
	 *            information like for parenthesis in context of loop or if
	 *            constructs.
	 * @return The HalsteadResult objects contains information about the current
	 *         node and it's countability.
	 */
	public HalsteadSymbol getHalsteadResult(ParserTree node);

}
