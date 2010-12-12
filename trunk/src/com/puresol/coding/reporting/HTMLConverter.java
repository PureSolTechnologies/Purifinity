package com.puresol.coding.reporting;

import com.puresol.coding.quality.QualityLevel;

public class HTMLConverter {

	public static String convertQualityLevelToHTML(QualityLevel qualityLevel) {
		if (qualityLevel == QualityLevel.HIGH) {
			return "<font color=\"#00c000\">" + qualityLevel.getIdentifier()
					+ "</font>";
		}
		if (qualityLevel == QualityLevel.MEDIUM) {
			return "<font color=\"#c0c000\">" + qualityLevel.getIdentifier()
					+ "</font>";
		}
		return "<font color=\"#c00000\">" + qualityLevel.getIdentifier()
				+ "</font>";
	}

}
