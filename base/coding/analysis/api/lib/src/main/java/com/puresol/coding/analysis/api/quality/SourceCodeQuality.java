/***************************************************************************
 *
 *   QualityLevel.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.api.quality;

/**
 * This enum stands for a quality level. There are three levels defined: low,
 * medium and high level.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SourceCodeQuality implements Comparable<SourceCodeQuality> {
	UNSPECIFIED(10), LOW(1), MEDIUM(2), HIGH(3);

	private final int level;

	private SourceCodeQuality(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public static SourceCodeQuality fromLevel(int level) {
		for (SourceCodeQuality qualityLevel : SourceCodeQuality.values()) {
			if (level == qualityLevel.getLevel()) {
				return qualityLevel;
			}
		}
		return SourceCodeQuality.UNSPECIFIED;
	}

	public static SourceCodeQuality getMinLevel(SourceCodeQuality... levels) {
		SourceCodeQuality resultLevel = UNSPECIFIED;
		for (SourceCodeQuality level : levels) {
			if (level.getLevel() < resultLevel.getLevel()) {
				resultLevel = level;
			}
		}
		return resultLevel;
	}
}
