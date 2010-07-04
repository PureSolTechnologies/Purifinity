/***************************************************************************
 *
 *   Analysis.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.java.samples;

import java.io.Serializable;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.quality.QualityLevel;

/**
 * This is a simple interface to a single code range analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Metric extends Serializable {

	public String getName();

	public CodeRange getCodeRange();

	public QualityLevel getQualityLevel();
}
