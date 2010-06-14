package com.puresol.coding.metrics.report;

import javax.i18n4j.Translator;

import com.puresol.coding.evaluator.metric.SLOCMetric;
import com.puresol.coding.evaluator.metric.report.HTMLMetricsReport;
import com.puresol.coding.evaluator.metric.report.SLOCReport;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.html.Anchor;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.statistics.Statistics;

public class SLOCReport {

	private static final Translator translator = Translator
			.getTranslator(SLOCReport.class);

	public static String getLineCountReport(SLOCMetric sloc) {
		String report = "phyLoc\t" + sloc.getPhyLOC() + "\t"
				+ translator.i18n("physical lines of code") + "\n";
		report += "proLoc\t" + sloc.getProLOC() + "\t"
				+ translator.i18n("productive lines of code") + "\n";
		report += "comLoc\t" + sloc.getComLOC() + "\t"
				+ translator.i18n("commented lines of code") + "\n";
		report += "blLoc\t" + sloc.getBlLOC() + "\t"
				+ translator.i18n("blank lines") + "\n";
		return report;
	}

	public static String getHTMLLineCountReport(SLOCMetric sloc) {
		return HTMLStandards.convertTSVToTable(getLineCountReport(sloc));
	}

	public static String getLineLengthReport(SLOCMetric sloc) {
		Statistics normal = sloc.getLineStatistics();
		Statistics trimmed = sloc.getTrimmedLineStatistics();
		Statistics prod = sloc.getProductiveLineStatistics();
		Statistics prodTrimmed = sloc.getTrimmedProductiveLineStatistics();
		String report = "\tnormal\ttrimmed\tproductive"
				+ "\tproductive trimmed\n";
		report += "avg\t" + Math.round(normal.getAvg() * 100.0) / 100.0 + "\t"
				+ Math.round(trimmed.getAvg() * 100.0) / 100.0 + "\t"
				+ Math.round(prod.getAvg() * 100.0) / 100.0 + "\t"
				+ Math.round(prodTrimmed.getAvg() * 100.0) / 100.0 + "\n";
		report += "median\t" + Math.round(normal.getMedian()) + "\t"
				+ Math.round(trimmed.getMedian()) + "\t"
				+ Math.round(prod.getMedian()) + "\t"
				+ Math.round(prodTrimmed.getMedian()) + "\n";
		report += "standard deviation\t"
				+ Math.round(normal.getStdDev() * 100.0) / 100.0 + "\t"
				+ Math.round(trimmed.getStdDev() * 100.0) / 100.0 + "\t"
				+ Math.round(prod.getStdDev() * 100.0) / 100.0 + "\t"
				+ Math.round(prodTrimmed.getStdDev() * 100.0) / 100.0 + "\n";
		report += "min\t" + Math.round(normal.getMin()) + "\t"
				+ Math.round(trimmed.getMin()) + "\t"
				+ Math.round(prod.getMin()) + "\t"
				+ Math.round(prodTrimmed.getMin()) + "\n";
		report += "max\t" + Math.round(normal.getMax()) + "\t"
				+ Math.round(trimmed.getMax()) + "\t"
				+ Math.round(prod.getMax()) + "\t"
				+ Math.round(prodTrimmed.getMax()) + "\n";
		return report;
	}

	public static String getHTMLLineLengthReport(SLOCMetric sloc) {
		return HTMLStandards.convertTSVToTable(getLineLengthReport(sloc));
	}

	public static String getHTMLReport(SLOCMetric sloc) {
		String report = Anchor.generate(sloc.getName(), "<h3>"
				+ translator.i18n("SLOC Metrics") + "</h3>");
		if (sloc != null) {
			report += HTMLConverter.convertQualityLevelToHTML(sloc
					.getQualityLevel());
			report += "<br/>";
			report += "<b>Line Counts</b>";
			report += getHTMLLineCountReport(sloc);
			report += "<b>Line Lengths</b>";
			report += getHTMLLineLengthReport(sloc);
		} else {
			report += HTMLMetricsReport.notMeasurableForCodeRangeMessage();
		}
		return report;

	}
}
