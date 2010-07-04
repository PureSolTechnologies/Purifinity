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

import javax.i18n4j.Translator;

import com.puresol.data.Identifiable;

/**
 * This enum stands for a quality level. There are three levels defined: low,
 * medium and high level.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum QualityLevel implements Identifiable, Comparable<QualityLevel> {
	UNSPECIFIED {
		@Override
		public String getIdentifier() {
			return translator.i18n("unspecified");
		}

		@Override
		public int getLevel() {
			return 10;
		}
	},
	LOW {
		@Override
		public String getIdentifier() {
			return translator.i18n("low");
		}

		@Override
		public int getLevel() {
			return 1;
		}
	},
	MEDIUM {
		@Override
		public String getIdentifier() {
			return translator.i18n("medium");
		}

		@Override
		public int getLevel() {
			return 2;
		}
	},
	HIGH {
		@Override
		public String getIdentifier() {
			return translator.i18n("high");
		}

		@Override
		public int getLevel() {
			return 3;
		}
	};

	private static final Translator translator = Translator
			.getTranslator(QualityLevel.class);

	public abstract int getLevel();

	@Override
	public abstract String getIdentifier();

	public static QualityLevel fromLevel(int level) {
		for (QualityLevel qualityLevel : QualityLevel.values()) {
			if (level == qualityLevel.getLevel()) {
				return qualityLevel;
			}
		}
		return QualityLevel.UNSPECIFIED;
	}

	public static QualityLevel getMinLevel(QualityLevel level1,
			QualityLevel level2) {
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
