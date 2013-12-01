package com.puresoltechnologies.purifinity.coding.metrics.mccabe;

import java.io.Serializable;

import com.puresoltechnologies.purifinity.uhura.ust.AbstractProduction;

/**
 * This interface is used to implement a part of the McCabe metric (cyclomatic
 * complexity) implementation within language packages. This implementation is
 * used to distinguish between node which increase the cyclomatic complexity and
 * those who don't.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface LanguageDependedMcCabeMetric extends Serializable {

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of node in context to the language.
	 * 
	 * @param node
	 *            is the node to be checked.
	 * @return True is returned in cases the node increases the cyclomatic
	 *         complexity within source codes.
	 */
	public int increasesCyclomaticComplexityBy(AbstractProduction production);

}
