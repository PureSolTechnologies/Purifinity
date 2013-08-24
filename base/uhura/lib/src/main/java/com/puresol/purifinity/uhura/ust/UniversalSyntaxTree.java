package com.puresol.purifinity.uhura.ust;

import java.io.Serializable;

import com.puresol.commons.trees.Tree;

/**
 * This is the central interface for universal syntax trees.
 * <p>
 * The condition for the syntax tree:
 * <ol>
 * <li>The UST must be immutable.</li>
 * <li>The UST may contain special classes implementing this interface which
 * contain meta information about the special node.</li>
 * </ol>
 * </p>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface UniversalSyntaxTree extends Tree<UniversalSyntaxTree>,
		Serializable {

}
