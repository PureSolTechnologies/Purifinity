package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.AbstractEntropyMetric;
import com.puresol.html.HTMLStandards;

public class EntropyReport {

	private static final Translator translator = Translator
			.getTranslator(EntropyReport.class);

	public static String getReport(AbstractEntropyMetric entropy) {
		String report = "n\t" + Math.round(entropy.get_n() * 100.0) / 100.0
				+ "\t" + translator.i18n("Vocabulary size") + "\n";
		report += "N\t" + Math.round(entropy.get_N() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Program length") + "\n";
		report += "Entropy\t" + Math.round(entropy.getEntropy() * 100.0)
				/ 100.0 + "\t" + translator.i18n("entropy") + "\n";
		report += "maxEntropy\t" + Math.round(entropy.getMaxEntropy() * 100.0)
				/ 100.0 + "\t" + translator.i18n("maximized entropy") + "\n";
		report += ">normEntropy\t"
				+ Math.round(entropy.getNormEntropy() * 100.0) / 100.0 + "\t"
				+ translator.i18n("normalized entropy") + "\n";
		report += "Entropy Redundance\t"
				+ Math.round(entropy.getEntropyRedundancy() * 100.0) / 100.0
				+ "\t" + translator.i18n("redundance in entropy") + "\n";
		report += "Redundance\t" + Math.round(entropy.getRedundancy() * 100.0)
				/ 100.0 + "\t"
				+ translator.i18n("number of redundand vokables") + "\n";
		report += "normRedundancy\t"
				+ Math.round(entropy.getNormRedundancy() * 100.0) / 100.0
				+ "\t" + translator.i18n("ratio of redundand vocables") + "\n";
		return report;
	}

	public static String getHTMLReport(AbstractEntropyMetric entropy) {
		String report = "<h2>Entropy from Information Theory</h2>";
		if (entropy != null) {
			report += ReportStandards.getQualitySign(entropy);
			report += "<br/>";
			report += HTMLStandards.convertTSVToTable(getReport(entropy));
		} else {
			report += "<p>No measureable for this kind of code range!</p>";
		}
		return report;
	}

}
