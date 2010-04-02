/***************************************************************************
 *
 *   Analysis.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.evaluator.metric;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.QualityLevel;

/**
 * This is a simple interface to a single code range analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Metric {

	public String getName();

	public CodeRange getCodeRange();

	public QualityLevel getQualityLevel();
}
