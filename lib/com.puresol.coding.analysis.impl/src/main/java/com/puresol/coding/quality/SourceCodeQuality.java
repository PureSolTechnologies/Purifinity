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

/**
 * This enum stands for a quality level. There are three levels defined: low,
 * medium and high level.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SourceCodeQuality implements Comparable<SourceCodeQuality> {
    UNSPECIFIED {
	@Override
	public String getIdentifier() {
	    return "unspecified";
	}

	@Override
	public int getLevel() {
	    return 10;
	}
    },
    LOW {
	@Override
	public String getIdentifier() {
	    return "low";
	}

	@Override
	public int getLevel() {
	    return 1;
	}
    },
    MEDIUM {
	@Override
	public String getIdentifier() {
	    return "medium";
	}

	@Override
	public int getLevel() {
	    return 2;
	}
    },
    HIGH {
	@Override
	public String getIdentifier() {
	    return "high";
	}

	@Override
	public int getLevel() {
	    return 3;
	}
    };

    public abstract int getLevel();

    public abstract String getIdentifier();

    public static SourceCodeQuality fromLevel(int level) {
	for (SourceCodeQuality qualityLevel : SourceCodeQuality.values()) {
	    if (level == qualityLevel.getLevel()) {
		return qualityLevel;
	    }
	}
	return SourceCodeQuality.UNSPECIFIED;
    }

    public static SourceCodeQuality getMinLevel(SourceCodeQuality level1,
	    SourceCodeQuality level2) {
	if ((level1 == null) && (level2 == null)) {
	    throw new NullPointerException("Both levels are null!");
	}
	if (level1 == null) {
	    return level2;
	}
	if (level2 == null) {
	    return level1;
	}
	if (level1.getLevel() < level2.getLevel()) {
	    return level1;
	}
	return level2;
    }
}
