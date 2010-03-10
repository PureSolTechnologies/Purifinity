package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.AvailableMetrics;
import com.puresol.coding.analysis.metrics.CodeDepth;
import com.puresol.html.Anchor;

public class CodeDepthReport {

    private static final Translator translator =
	    Translator.getTranslator(CodeDepthReport.class);

    public static String getReport(CodeDepth codeDepth) {
	return "";
    }

    public static String getHTMLReport(CodeDepth codeDepth) {
	String report =
		Anchor.generate(AvailableMetrics.CODE_DEPTH
			.getIdentifier(), "<h2>"
			+ translator.i18n("CodeDepth") + "</h2>");
	if (codeDepth != null) {
	    report += ReportStandards.getQualitySign(codeDepth);
	    report += "<br/>";
	    report +=
		    "<p>" + translator.i18n("Maximum code depth: ")
			    + "</p>";
	    report += codeDepth.getMaxLayer();
	} else {
	    report +=ReportStandards.notMeasurableForCodeRangeMessage();
	}
	return report;
    }
}
