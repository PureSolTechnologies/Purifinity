package com.puresol.coding.reporting;

import com.puresol.coding.quality.SourceCodeQuality;

public class HTMLConverter {

	public static String convertQualityLevelToHTML(SourceCodeQuality qualityLevel) {
		if (qualityLevel == SourceCodeQuality.HIGH) {
			return "<font color=\"#00c000\">" + qualityLevel.getIdentifier()
					+ "</font>";
		}
		if (qualityLevel == SourceCodeQuality.MEDIUM) {
			return "<font color=\"#c0c000\">" + qualityLevel.getIdentifier()
					+ "</font>";
		}
		return "<font color=\"#c00000\">" + qualityLevel.getIdentifier()
				+ "</font>";
	}

}
