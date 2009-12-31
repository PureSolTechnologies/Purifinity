/***************************************************************************
 *
 *   Analysis.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

/**
 * This is a simple interface to a single code range analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Analysis {

	public CodeRange getCodeRange();

	public QualityLevel getQualityLevel();
}
