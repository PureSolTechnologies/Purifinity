package com.puresol.coding.evaluator.metric.report;

import javax.i18n4j.Translator;

import com.puresol.coding.evaluator.metric.MaintainabilityIndex;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.html.Anchor;
import com.puresol.reporting.html.HTMLStandards;

public class MaintainabilityReport {

	private static final Translator translator = Translator
			.getTranslator(MaintainabilityReport.class);

	public static String getReport(MaintainabilityIndex maintainability) {
		String report = "MIwoc\t"
				+ Math.round(maintainability.getMIWoc() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Maintainability index without comments")
				+ "\n";
		report += "MIcw\t" + Math.round(maintainability.getMIcw() * 100.0)
				/ 100.0 + "\t"
				+ translator.i18n("Maintainability index comment weight")
				+ "\n";
		report += "MI\t" + Math.round(maintainability.getMI() * 100.0) / 100.0
				+ "\t" + translator.i18n("Maintainability index") + "\n";
		return report;
	}

	public static String getHTMLReport(MaintainabilityIndex maintainabilityIndex) {
		String report = Anchor.generate(maintainabilityIndex.getName(), "<h2>"
				+ translator.i18n("Maintainability Index") + "</h2>");
		if (maintainabilityIndex != null) {
			report += HTMLConverter
					.convertQualityLevelToHTML(maintainabilityIndex
							.getQualityLevel());
			report += "<br/>";
			report += HTMLStandards
					.convertTSVToTable(getReport(maintainabilityIndex));
		} else {
			report += HTMLMetricsReport.notMeasurableForCodeRangeMessage();
		}
		return report;
	}
}
