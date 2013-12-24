/***************************************************************************
 *
 *   QualityLevel.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.evaluation.domain;

/**
 * This enum stands for a quality level. There are three levels defined: low,
 * medium and high level.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SourceCodeQuality implements Comparable<SourceCodeQuality> {
	LOW, MEDIUM, HIGH, UNSPECIFIED, ;

	/**
	 * This method returns the minimum quality.
	 * 
	 * @param qualities
	 *            are the {@link SourceCodeQuality}s where the minimum is to be
	 *            found of.
	 * @return The minimum {@link SourceCodeQuality} is returned.
	 */
	public static SourceCodeQuality getMinimum(SourceCodeQuality... qualities) {
		SourceCodeQuality minQuality = UNSPECIFIED;
		for (SourceCodeQuality quality : qualities) {
			if (quality.ordinal() < minQuality.ordinal()) {
				minQuality = quality;
			}
		}
		return minQuality;
	}
}
