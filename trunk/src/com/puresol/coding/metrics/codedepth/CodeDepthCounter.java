package com.puresol.coding.metrics.codedepth;

import com.puresol.uhura.ast.ParserTree;

/**
 * This interface is used for CodeDepth. The interface is implemented language
 * depended wihtin a language pack. The implementation is retrieved via
 * getImplementation(Class<?>) and used to decide how deep the specified AST is.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeDepthCounter {

	public int count(ParserTree ast);

}
