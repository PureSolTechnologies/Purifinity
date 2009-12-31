/***************************************************************************
 *
 *   HTMLAnalysisReport.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import javax.i18n4j.Translator;

import com.puresol.html.HTMLStandards;

public class HTMLAnalysisReport extends AbstractAnalysisReport {

    private static final Translator translator =
	    Translator.getTranslator(HTMLAnalysisReport.class);

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
	return "<h1> AnalysisReport for " + range.getType().getName()
		+ " '" + range.getName() + "'</h1>";
    }

    private String getOverview() {
	String report = "<h2>Overview</h2>";
	AbstractSLOCMetric sloc = getMetrics().getSLOCMetric();
	AbstractMcCabeMetric mcCabe = getMetrics().getMcCabeMetric();
	AbstractHalsteadMetric halstead = getMetrics().getHalsteadMetric();
	AbstractMaintainabilityIndex maintainability =
		getMetrics().getMaintainabilityIndex();
	AbstractEntropyMetric entropy = getMetrics().getEntropyMetric();
	report += "<table>";
	if (sloc != null) {
	    report +=
		    "<tr><td>SLOC Metric</td><td>"
			    + getQualitySign(sloc)
			    + "</td><td>"
			    + translator
				    .i18n("Statistics on source code fo lines")
			    + "</td></tr>";
	}
	if (mcCabe != null) {
	    report +=
		    "<tr><td>McCabe Metric</td><td>"
			    + getQualitySign(mcCabe) + "</td><td>"
			    + translator.i18n("Cyclomatic number")
			    + "</td></tr>";
	}
	if (halstead != null) {
	    report +=
		    "<tr><td>Halstead Metric</td><td>"
			    + getQualitySign(halstead)
			    + "</td><td>"
			    + translator
				    .i18n("Statistics on operators and operands")
			    + "</td></tr>";
	}
	if (maintainability != null) {
	    report +=
		    "<tr><td>Maintainability Index</td><td>"
			    + getQualitySign(maintainability)
			    + "</td><td>"
			    + translator.i18n("Maintainability Index")
			    + "</td></tr>";
	}
	if (entropy != null) {
	    report +=
		    "<tr><td>Entropy Metric</td><td>"
			    + getQualitySign(entropy)
			    + "</td><td>"
			    + translator
				    .i18n("Inspections on basis of entropy calculations")
			    + "</td></tr>";
	}
	report += "</table>";
	return report;
    }

    private String getMetricReport() {
	String report = getSLOCReport();
	report += getMcCabeReport();
	report += getHalsteadReport();
	report += getMaintainabilityReport();
	report += getEntropyReport();
	report += getSourceCode();
	return report;
    }

    private String getSLOCReport() {
	String report = "<h2>SLOC Metrics</h2>";
	AbstractSLOCMetric sloc = getMetrics().getSLOCMetric();
	if (sloc != null) {
	    report += getQualitySign(sloc);
	    report += "<br/>";
	    report += "<h3>Line Counts</h3>";
	    report += sloc.getHTMLLineCountReport();
	    report += "<h3>Line Lengths</h3>";
	    report += sloc.getHTMLLineLengthReport();
	} else {
	    report += "<p>No measureable for this kind of code range!</p>";
	}
	return report;
    }

    private String getMcCabeReport() {
	String report = "<h2>McCabe Cyclomatic Number</h2>";
	AbstractMcCabeMetric mcCabe = getMetrics().getMcCabeMetric();
	if (mcCabe != null) {
	    report += getQualitySign(mcCabe);
	    report += "<br/>";
	    report +=
		    translator.i18n("Cyclomatic number v(G)") + "="
			    + mcCabe.getCyclomaticNumber();
	} else {
	    report += "<p>No measureable for this kind of code range!</p>";
	}
	return report;
    }

    private String getHalsteadReport() {
	String report = "<h2>Halstead Metric</h2>";
	AbstractHalsteadMetric halstead = getMetrics().getHalsteadMetric();
	if (halstead != null) {
	    report += getQualitySign(halstead);
	    report += "<br/>";
	    report += halstead.getHTMLReport();

	    report += "<h3>Operators</h3>";
	    report += halstead.getHTMLOperatorReport();

	    report += "<h3>Operands</h3>";
	    report += halstead.getHTMLOperantReport();
	} else {
	    report += "<p>No measureable for this kind of code range!</p>";
	}
	return report;
    }

    private String getMaintainabilityReport() {
	String report = "<h2>Maintainability Index</h2>";
	AbstractMaintainabilityIndex maintainability =
		getMetrics().getMaintainabilityIndex();
	if (maintainability != null) {
	    report += getQualitySign(maintainability);
	    report += "<br/>";
	    report += maintainability.getHTMLReport();
	} else {
	    report += "<p>No measureable for this kind of code range!</p>";
	}
	return report;
    }

    private String getEntropyReport() {
	String report = "<h2>Entropy from Information Theory</h2>";
	AbstractEntropyMetric entropy = getMetrics().getEntropyMetric();
	if (entropy != null) {
	    report += getQualitySign(entropy);
	    report += "<br/>";
	    report += entropy.getHTMLReport();
	} else {
	    report += "<p>No measureable for this kind of code range!</p>";
	}
	return report;
    }

    private String getSourceCode() {
	String report = "<h2>" + translator.i18n("Source Code") + "</h2>";
	report += "<tt>";
	report +=
		getMetrics().getCodeRange().getText().replaceAll("\n",
			"<br/>").replaceAll(" ", "&nbsp;").replaceAll(
			"\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
	report += "</tt>";
	return report;
    }

    private String getQualitySign(Analysis analysis) {
	if (analysis.getQualityLevel() == QualityLevel.HIGH) {
	    return "<font color=\"#00ff00\">HIGH</font>";
	}
	if (analysis.getQualityLevel() == QualityLevel.MEDIUM) {
	    return "<font color=\"#ffff00\">MEDIUM</font>";
	}
	return "<font color=\"#ff0000\">LOW</font>";
    }

    public QualityLevel getQualityLevel() {
	return getMetrics().getQualityLevel();
    }

}
