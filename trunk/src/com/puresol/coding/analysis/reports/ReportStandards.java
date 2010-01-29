package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeDepth;
import com.puresol.coding.analysis.EntropyMetric;
import com.puresol.coding.analysis.HalsteadMetric;
import com.puresol.coding.analysis.MaintainabilityIndex;
import com.puresol.coding.analysis.McCabeMetric;
import com.puresol.coding.analysis.Metric;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.coding.analysis.SLOCMetric;

public class ReportStandards {

    private static final Translator translator =
	    Translator.getTranslator(ReportStandards.class);

    public static String getQualitySign(Metric analysis) {
	if (analysis.getQualityLevel() == QualityLevel.HIGH) {
	    return "<font color=\"#00ff00\">HIGH</font>";
	}
	if (analysis.getQualityLevel() == QualityLevel.MEDIUM) {
	    return "<font color=\"#ffff00\">MEDIUM</font>";
	}
	return "<font color=\"#ff0000\">LOW</font>";
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

    public static String notMeasurableForCodeRangeMessage() {
	return "<p>"
		+ translator
			.i18n("No measureable for this kind of code range!")
		+ "</p>";
    }
}
