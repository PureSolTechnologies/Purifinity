/***************************************************************************
 *
 *   QualityLevel.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.purifinity.coding.evaluation.api;

/**
 * This enum stands for a quality level. There are three levels defined: low,
 * medium and high level.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SourceCodeQuality implements Comparable<SourceCodeQuality> {
	LOW, MEDIUM, HIGH, UNSPECIFIED, ;

	public static SourceCodeQuality getMinLevel(SourceCodeQuality... levels) {
		SourceCodeQuality resultLevel = UNSPECIFIED;
		for (SourceCodeQuality level : levels) {
			if (level.ordinal() < resultLevel.ordinal()) {
				resultLevel = level;
			}
		}
		return resultLevel;
	}
}
