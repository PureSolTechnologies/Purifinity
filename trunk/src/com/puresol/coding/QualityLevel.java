package com.puresol.coding;

public enum QualityLevel {
    LOW(1), MEDIUM(2), HIGH(3);

    private int level;

    private QualityLevel(int level) {
	this.level = level;
    }

    public int getLevel() {
	return level;
    }

    public static QualityLevel getMinLevel(QualityLevel level1,
	    QualityLevel level2) {
	if ((level1 == null) && (level2 == null)) {
	    throw new IllegalArgumentException("Both levels are null!");
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
