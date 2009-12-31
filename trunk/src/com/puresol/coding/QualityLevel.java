/***************************************************************************
 *
 *   QualityLevel.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

/**
 * This enum stands for a quality level. There are three levels defined: low,
 * medium and high level.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
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
