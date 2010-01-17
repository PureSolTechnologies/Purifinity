package com.puresol.coding.analysis.reports;

import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.QualityLevel;

public class ReportStandards {

	public static String getQualitySign(Analysis analysis) {
		if (analysis.getQualityLevel() == QualityLevel.HIGH) {
			return "<font color=\"#00ff00\">HIGH</font>";
		}
		if (analysis.getQualityLevel() == QualityLevel.MEDIUM) {
			return "<font color=\"#ffff00\">MEDIUM</font>";
		}
		return "<font color=\"#ff0000\">LOW</font>";
	}

	public static String convertSourceCodeToHTML(String sourceCode) {
		String sourceCodeHTML = "<tt>";
		sourceCodeHTML += sourceCode.replaceAll("\n", "<br/>").replaceAll(" ",
				"&nbsp;").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		sourceCodeHTML += "</tt>";
		return sourceCodeHTML;
	}
}
