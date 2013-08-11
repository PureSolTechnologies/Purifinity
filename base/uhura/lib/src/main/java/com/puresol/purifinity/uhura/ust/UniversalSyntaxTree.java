package com.puresol.purifinity.uhura.ust;

import java.io.Serializable;

import com.puresol.commons.trees.Tree;

/**
 * This is the central interface for all {@link UniversalSyntaxTree} classes.
 * The main purpose is to specify the base interface {@link Tree} and the
 * globally needed methods.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface UniversalSyntaxTree extends Tree<UniversalSyntaxTree>,
		Serializable {

}
