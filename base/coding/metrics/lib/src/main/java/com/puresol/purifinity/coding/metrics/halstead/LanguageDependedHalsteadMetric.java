package com.puresol.purifinity.coding.metrics.halstead;

import java.io.Serializable;

import com.puresol.purifinity.uhura.ust.terminal.AbstractTerminal;

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
	 * This method returns whether a specified token is an operator.
	 * 
	 * @param name
	 *            is the name of the token to be checked to be an operator.
	 * @return <code>true</code> if the token is an operator. <code>false</code>
	 *         is returned otherwise.
	 */
	public boolean isOperator(String name);

	/**
	 * This method returns whether a specified token is an operand.
	 * 
	 * @param name
	 *            is the name of the token to be checked to be an operand.
	 * @return <code>true</code> if the token is an operand. <code>false</code>
	 *         is returned otherwise.
	 */
	public boolean isOperand(String name);

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
	public HalsteadSymbol getHalsteadResult(AbstractTerminal node);

}
