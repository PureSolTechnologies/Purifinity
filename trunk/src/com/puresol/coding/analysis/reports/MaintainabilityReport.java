package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.AvailableMetrics;
import com.puresol.coding.analysis.MaintainabilityIndex;
import com.puresol.html.Anchor;
import com.puresol.html.HTMLStandards;

public class MaintainabilityReport {

    private static final Translator translator =
	    Translator.getTranslator(MaintainabilityReport.class);

    public static String getReport(MaintainabilityIndex maintainability) {
	String report =
		"MIwoc\t"
			+ Math.round(maintainability.getMIWoc() * 100.0)
			/ 100.0
			+ "\t"
			+ translator
				.i18n("Maintainability index without comments")
			+ "\n";
	report +=
		"MIcw\t"
			+ Math.round(maintainability.getMIcw() * 100.0)
			/ 100.0
			+ "\t"
			+ translator
				.i18n("Maintainability index comment weight")
			+ "\n";
	report +=
		"MI\t" + Math.round(maintainability.getMI() * 100.0)
			/ 100.0 + "\t"
			+ translator.i18n("Maintainability index") + "\n";
	return report;
    }

    public static String getHTMLReport(
	    MaintainabilityIndex maintainabilityIndex) {
	String report =
		Anchor.generate(AvailableMetrics.MAINTAINABILITY
			.getIdentifier(), "<h2>"
			+ translator.i18n("Maintainability Index")
			+ "</h2>");
	if (maintainabilityIndex != null) {
	    report += ReportStandards.getQualitySign(maintainabilityIndex);
	    report += "<br/>";
	    report +=
		    HTMLStandards
			    .convertTSVToTable(getReport(maintainabilityIndex));
	} else {
	    report += ReportStandards.notMeasurableForCodeRangeMessage();
	}
	return report;
    }
}
