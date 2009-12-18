package com.puresol.coding;

import java.util.Hashtable;

import javax.i18n4j.Translator;

import com.puresol.html.HTMLStandards;

public class HTMLAnalysisReport extends
	AbstractAnalysisReport {

    private static final Translator translator =
	    Translator
		    .getTranslator(HTMLAnalysisReport.class);

    public HTMLAnalysisReport(CodeRangeMetrics metrics) {
	super(metrics);
    }

    public String getReport() {
	String report = HTMLStandards.getStandardHeader();
	report += getTitle();
	report += getOverview();
	report += getMetricReport();
	report += HTMLStandards.getStandardFooter();
	return report;
    }

    private String getTitle() {
	CodeRange range = getMetrics().getCodeRange();
	return "<h1> AnalysisReport for "
		+ range.getType().getName() + " '"
		+ range.getName() + "'</h1>";
    }

    private String getOverview() {
	String report = "<h2>Overview</h2>";
	SLOCMetric sloc = getMetrics().getSLOCMetric();
	McCabeMetric mcCabe =
		getMetrics().getMcCabeMetric();
	HalsteadMetric halstead =
		getMetrics().getHalsteadMetric();
	MaintainabilityIndex maintainability =
		getMetrics().getMaintainabilityIndex();
	EntropyMetric entropy =
		getMetrics().getEntropyMetric();
	report += "<table>";
	report +=
		"<tr><td>SLOC Metric</td><td>"
			+ getQualitySign(sloc)
			+ "</td><td>"
			+ translator
				.i18n("Statistics on source code fo lines")
			+ "</td></tr>";
	report +=
		"<tr><td>McCabe Metric</td><td>"
			+ getQualitySign(mcCabe)
			+ "</td><td>"
			+ translator
				.i18n("Cyclomatic number")
			+ "</td></tr>";
	report +=
		"<tr><td>Halstead Metric</td><td>"
			+ getQualitySign(halstead)
			+ "</td><td>"
			+ translator
				.i18n("Statistics on operators and operands")
			+ "</td></tr>";
	report +=
		"<tr><td>Maintainability Index</td><td>"
			+ getQualitySign(maintainability)
			+ "</td><td>"
			+ translator
				.i18n("Maintainability Index")
			+ "</td></tr>";
	report +=
		"<tr><td>Entropy Metric</td><td>"
			+ getQualitySign(entropy)
			+ "</td><td>"
			+ translator
				.i18n("Inspections on basis of entropy calculations")
			+ "</td></tr>";
	report += "</table>";
	return report;
    }

    private String getMetricReport() {
	String report = getSLOCReport();
	report += getMcCabeReport();
	report += getHalsteadReport();
	report += getMaintainabilityReport();
	report += getEntropyReport();
	report += getSource();
	return report;
    }

    private String getSLOCReport() {
	String report = "<h2>SLOC Metrics</h2>";
	SLOCMetric sloc = getMetrics().getSLOCMetric();
	report += getQualitySign(sloc);
	report += "<br/>";
	report += "<h3>Line Counts</h3>";
	report += sloc.getHTMLLineCountReport();
	report += "<h3>Line Lengths</h3>";
	report += sloc.getHTMLLineLengthReport();
	return report;
    }

    private String getMcCabeReport() {
	String report = "<h2>McCabe Cyclomatic Number</h2>";
	McCabeMetric mcCabe =
		getMetrics().getMcCabeMetric();
	report += getQualitySign(mcCabe);
	report += "<br/>";
	report +=
		translator.i18n("Cyclomatic number v(G)")
			+ "="
			+ mcCabe.getCyclomaticNumber();
	return report;
    }

    private String getHalsteadReport() {
	String report = "<h2>Halstead Metric</h2>";
	HalsteadMetric halstead =
		getMetrics().getHalsteadMetric();
	report += getQualitySign(halstead);
	report += "<br/>";
	report += halstead.getHTMLReport();

	report += "<h3>Operators</h3>";
	report += "<table>";
	Hashtable<String, Integer> operators =
		halstead.getOperators();
	for (String operator : operators.keySet()) {
	    int number = operators.get(operator);
	    report +=
		    "<tr><td>"
			    + HTMLStandards
				    .toHTML(operator)
			    + "</td><td>" + number
			    + "</td></tr>";
	}
	report += "</table>";

	report += "<h3>Operands</h3>";
	report += "<table>";
	Hashtable<String, Integer> operands =
		halstead.getOperands();
	for (String operand : operands.keySet()) {
	    int number = operands.get(operand);
	    report +=
		    "<tr><td>"
			    + HTMLStandards.toHTML(operand)
			    + "</td><td>" + number
			    + "</td></tr>";
	}
	report += "</table>";
	return report;
    }

    private String getMaintainabilityReport() {
	String report = "<h2>Maintainability Index</h2>";
	MaintainabilityIndex maintainability =
		getMetrics().getMaintainabilityIndex();
	report += getQualitySign(maintainability);
	report += "<br/>";
	report += "<table>";
	report +=
		"<tr><td>MIwoc</td><td>"
			+ round(maintainability.getMIWoc())
			+ "</td><td>"
			+ translator
				.i18n("Maintainability index without comments")
			+ "</td></tr>";
	report +=
		"<tr><td>MIcw</td><td>"
			+ round(maintainability.getMIcw())
			+ "</td><td>"
			+ translator
				.i18n("Maintainability index comment weight")
			+ "</td></tr>";
	report +=
		"<tr><td><b>MI</b></td><td><b>"
			+ round(maintainability.getMI())
			+ "</b></td><td><b>"
			+ translator
				.i18n("Maintainability index")
			+ "</b></td></tr>";
	report += "</table>";
	return report;
    }

    private String getEntropyReport() {
	String report =
		"<h2>Entropy from Information Theory</h2>";
	EntropyMetric entropy =
		getMetrics().getEntropyMetric();
	report += getQualitySign(entropy);
	report += "<br/>";
	report += "<table>";
	report +=
		"<tr><td>n</td><td>"
			+ round(entropy.get_n())
			+ "</td><td>"
			+ translator
				.i18n("Vocabulary size")
			+ "</td></tr>";
	report +=
		"<tr><td>N</td><td>"
			+ round(entropy.get_N())
			+ "</td><td>"
			+ translator.i18n("Program length")
			+ "</td></tr>";
	report +=
		"<tr><td>Entropy</td><td>"
			+ round(entropy.getEntropy())
			+ "</td><td>"
			+ translator.i18n("entropy")
			+ "</td></tr>";
	report +=
		"<tr><td>maxEntropy</td><td>"
			+ round(entropy.getMaxEntropy())
			+ "</td><td>"
			+ translator
				.i18n("maximized entropy")
			+ "</td></tr>";
	report +=
		"<tr><td>normEntropy</td><td>"
			+ round(entropy.getNormEntropy())
			+ "</td><td>"
			+ translator
				.i18n("normalized entropy")
			+ "</td></tr>";
	report +=
		"<tr><td>Entropy Redundance</td><td>"
			+ round(entropy
				.getEntropyRedundancy())
			+ "</td><td>"
			+ translator
				.i18n("redundance in entropy")
			+ "</td></tr>";
	report +=
		"<tr><td>Redundance</td><td>"
			+ round(entropy.getRedundancy())
			+ "</td><td>"
			+ translator
				.i18n("number of redundand vokables")
			+ "</td></tr>";
	report +=
		"<tr><td>normRedundancy</td><td>"
			+ round(entropy.getNormRedundancy())
			+ "</td><td>"
			+ translator
				.i18n("ratio of redundand vocables")
			+ "</td></tr>";
	report += "</table>";
	return report;
    }

    private String getSource() {
	String report =
		"<h2>" + translator.i18n("Source Code")
			+ "</h2>";
	report += "<tt>";
	report +=
		getMetrics().getCodeRange().getText()
			.replaceAll("\n", "<br/>")
			.replaceAll(" ", "&nbsp;")
			.replaceAll("\t",
				"&nbsp;&nbsp;&nbsp;&nbsp;");
	report += "</tt>";
	return report;
    }

    private String getQualitySign(Analysis analysis) {
	if (analysis.getQualityLevel() == QualityLevel.HIGH) {
	    return "<font bgcolor=\"#00ff00\">HIGH</font>";
	}
	if (analysis.getQualityLevel() == QualityLevel.MEDIUM) {
	    return "<font bgcolor=\"#ffff00\">MEDIUM</font>";
	}
	return "<font bgcolor=\"#ff0000\">LOW</font>";
    }

    private double round(double d) {
	return Math.round(d * 100.0) / 100.0;
    }

    public QualityLevel getQualityLevel() {
	return getMetrics().getQualityLevel();
    }
}
