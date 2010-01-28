package com.puresol.coding.analysis.reports;

import com.puresol.coding.analysis.CodeDepth;
import com.puresol.coding.analysis.EntropyMetric;
import com.puresol.coding.analysis.HalsteadMetric;
import com.puresol.coding.analysis.MaintainabilityIndex;
import com.puresol.coding.analysis.McCabeMetric;
import com.puresol.coding.analysis.Metric;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.coding.analysis.SLOCMetric;

public class ReportStandards {

    public static String getQualitySign(Metric analysis) {
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
	sourceCodeHTML +=
		sourceCode.replaceAll("\n", "<br/>").replaceAll(" ",
			"&nbsp;").replaceAll("\t",
			"&nbsp;&nbsp;&nbsp;&nbsp;");
	sourceCodeHTML += "</tt>";
	return sourceCodeHTML;
    }

    public static String getReport(Metric metric) {
	if (metric instanceof SLOCMetric) {
	    return SLOCReport.getHTMLReport((SLOCMetric) metric);
	}
	if (metric instanceof CodeDepth) {
	    return CodeDepthReport.getHTMLReport((CodeDepth) metric);
	}
	if (metric instanceof McCabeMetric) {
	    return McCabeReport.getHTMLReport((McCabeMetric) metric);
	}
	if (metric instanceof HalsteadMetric) {
	    return HalsteadReport.getHTMLReport((HalsteadMetric) metric);
	}
	if (metric instanceof MaintainabilityIndex) {
	    return MaintainabilityReport
		    .getHTMLReport((MaintainabilityIndex) metric);
	}
	if (metric instanceof EntropyMetric) {
	    return EntropyReport.getHTMLReport((EntropyMetric) metric);
	}
	throw new IllegalArgumentException();
    }
}
