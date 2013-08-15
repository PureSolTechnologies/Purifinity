package com.puresol.purifinity.uhura.ust;

import com.puresol.commons.trees.TreeException;
import com.puresol.purifinity.uhura.parser.ParserTree;

/**
 * This interface is to be implemented for classes which convert
 * {@link ParserTree} objects into {@link AbstractUSTNode} objects.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface USTCreator {

	public USTNode createUST(ParserTree parserTree) throws TreeException;

}
