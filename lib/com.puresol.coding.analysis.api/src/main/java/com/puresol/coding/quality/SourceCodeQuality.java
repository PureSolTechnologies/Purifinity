/***************************************************************************
 *
 *   QualityLevel.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.quality;

import com.puresol.data.Identifiable;

/**
 * This enum stands for a quality level. There are three levels defined: low,
 * medium and high level.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SourceCodeQuality implements Comparable<SourceCodeQuality>,
	Identifiable {
    UNSPECIFIED(10, "unspecified"), LOW(1, "low"), MEDIUM(2, "medium"), HIGH(3,
	    "high");

    private final int level;
    private final String identifier;

    private SourceCodeQuality(int level, String identifier) {
	this.level = level;
	this.identifier = identifier;
    }

    public int getLevel() {
	return level;
    }

    @Override
    public String getIdentifier() {
	return identifier;
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
