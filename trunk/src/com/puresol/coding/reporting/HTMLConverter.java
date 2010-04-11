package com.puresol.coding.reporting;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.reporting.html.HTMLStandards;

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

	public static String convertCodeRangeToHTML(CodeRange codeRange) {
		String output = codeRange.getType().getIdentifier() + " \""
				+ codeRange.getName() + "\"<br/>\n";
		output += "(" + codeRange.getFile() + ":" + codeRange.getStartId() + "-"
				+ codeRange.getStopId();
		output += HTMLStandards.convertSourceCodeToHTML(codeRange.getText());
		return output;
	}

}
