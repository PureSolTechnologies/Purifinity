package com.puresol.coding.metrics.codedepth;

import com.puresol.uhura.ast.ParserTree;

/**
 * This interface is used to implement a part of the code depth metric
 * implementation within language packages. This implementation is used to
 * distinguish between node which are cascaded and those are not. Means, this
 * tests look for structural language blocks or statement groups.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface LanguageDependedCodeDepthMetric {

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of node in context to the language.
	 * 
	 * @param token
	 *            is the token to be checked.
	 * @return If true is returned the current node is a part of the language
	 *         specification which build an own structural block.
	 */
	public boolean cascadingNode(ParserTree node);

}
