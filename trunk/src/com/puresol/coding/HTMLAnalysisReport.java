package com.puresol.coding;

import javax.i18n4j.Translator;

import com.puresol.html.HTMLStandards;

public class HTMLAnalysisReport extends AbstractAnalysisReport {

	private static final Translator translator = Translator
			.getTranslator(HTMLAnalysisReport.class);

	public HTMLAnalysisReport(CodeRange range) {
		super(range);
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
		CodeRange range = getCodeRange();
		return "<h1> AnalysisReport for " + range.getType().getName() + " '"
				+ range.getName() + "'</h1>";
	}

	private String getOverview() {
		String report = "<h2>Overview</h2>";
		SLOCMetric sloc = getSLOCMetric();
		McCabeMetric mcCabe = getMcCabeMetric();
		HalsteadMetric halstead = getHalsteadMetric();
		MaintainabilityIndex maintainability = getMaintainabilityIndex();
		EntropyMetric entropy = getEntropyMetric();
		report += "<table>";
		report += "<tr><td>SLOC Metric</td><td>" + getQualitySign(sloc)
				+ "</td><td>"
				+ translator.i18n("Statistics on source code fo lines")
				+ "</td></tr>";
		report += "<tr><td>McCabe Metric</td><td>" + getQualitySign(mcCabe)
				+ "</td><td>" + translator.i18n("Cyclomatic number")
				+ "</td></tr>";
		report += "<tr><td>Halstead Metric</td><td>" + getQualitySign(halstead)
				+ "</td><td>"
				+ translator.i18n("Statistics on operators and operands")
				+ "</td></tr>";
		report += "<tr><td>Maintainability Index</td><td>"
				+ getQualitySign(maintainability) + "</td><td>"
				+ translator.i18n("Maintainability Index") + "</td></tr>";
		report += "<tr><td>Entropy Metric</td><td>"
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
		SLOCMetric sloc = getSLOCMetric();
		report += getQualitySign(sloc);
		report += "<br/>";
		report += "<table>";
		report += "<tr><td>phyLoc</td><td>" + sloc.getPhyLOC() + "</td><td>"
				+ translator.i18n("physical lines of code") + "</td></tr>";
		report += "<tr><td>proLoc</td><td>" + sloc.getProLOC() + "</td><td>"
				+ translator.i18n("productive lines of code") + "</td></tr>";
		report += "<tr><td>comLoc</td><td>" + sloc.getComLOC() + "</td><td>"
				+ translator.i18n("commented lines of code") + "</td></tr>";
		report += "<tr><td>blLoc</td><td>" + sloc.getBlLOC() + "</td><td>"
				+ translator.i18n("blank lines") + "</td></tr>";
		report += "</table>";
		return report;
	}

	private String getMcCabeReport() {
		String report = "<h2>McCabe Cyclomatic Number</h2>";
		McCabeMetric mcCabe = getMcCabeMetric();
		report += getQualitySign(mcCabe);
		report += "<br/>";
		report += translator.i18n("Cyclomatic number v(G)") + "="
				+ mcCabe.getCyclomaticNumber();
		return report;
	}

	private String getHalsteadReport() {
		String report = "<h2>Halstead Metric</h2>";
		HalsteadMetric halstead = getHalsteadMetric();
		report += getQualitySign(halstead);
		report += "<br/>";
		report += "<table>";
		report += "<tr><td>n1</td><td>" + halstead.get_n1() + "</td><td>"
				+ translator.i18n("Number of different operators")
				+ "</td></tr>";
		report += "<tr><td>N1</td><td>" + halstead.get_N1() + "</td><td>"
				+ translator.i18n("Total number operators") + "</td></tr>";
		report += "<tr><td>n2</td><td>" + halstead.get_n2() + "</td><td>"
				+ translator.i18n("Number of different operands")
				+ "</td></tr>";
		report += "<tr><td>N2</td><td>" + halstead.get_N2() + "</td><td>"
				+ translator.i18n("Total number of operands") + "</td></tr>";
		report += "<tr><td>n</td><td>" + round(halstead.get_n()) + "</td><td>"
				+ translator.i18n("Vocabulary size") + "</td></tr>";
		report += "<tr><td>N</td><td>" + round(halstead.get_N()) + "</td><td>"
				+ translator.i18n("Program length") + "</td></tr>";
		report += "<tr><td>HL</td><td>" + round(halstead.get_HL())
				+ "</td><td>" + translator.i18n("Halstead length")
				+ "</td></tr>";
		report += "<tr><td>HV</td><td>" + round(halstead.get_HV())
				+ "</td><td>" + translator.i18n("Halstead volume")
				+ "</td></tr>";
		report += "<tr><td>D</td><td>" + round(halstead.get_D()) + "</td><td>"
				+ translator.i18n("Difficulty level") + "</td></tr>";
		report += "<tr><td>L</td><td>" + round(halstead.get_L()) + "</td><td>"
				+ translator.i18n("Program level") + "</td></tr>";
		report += "<tr><td>E</td><td>" + round(halstead.get_E()) + "</td><td>"
				+ translator.i18n("Effort to implement") + "</td></tr>";
		report += "<tr><td>T</td><td>" + round(halstead.get_T()) + "</td><td>"
				+ translator.i18n("Implementatiom time") + "</td></tr>";
		report += "<tr><td>B</td><td>" + round(halstead.get_B()) + "</td><td>"
				+ translator.i18n("Number of delivered bugs") + "</td></tr>";
		report += "</table>";
		return report;
	}

	private String getMaintainabilityReport() {
		String report = "<h2>Maintainability Index</h2>";
		MaintainabilityIndex maintainability = getMaintainabilityIndex();
		report += getQualitySign(maintainability);
		report += "<br/>";
		report += "<table>";
		report += "<tr><td>MIwoc</td><td>" + round(maintainability.getMIWoc())
				+ "</td><td>"
				+ translator.i18n("Maintainability index without comments")
				+ "</td></tr>";
		report += "<tr><td>MIcw</td><td>" + round(maintainability.getMIcw())
				+ "</td><td>"
				+ translator.i18n("Maintainability index comment weight")
				+ "</td></tr>";
		report += "<tr><td><b>MI</b></td><td><b>"
				+ round(maintainability.getMI()) + "</b></td><td><b>"
				+ translator.i18n("Maintainability index") + "</b></td></tr>";
		report += "</table>";
		return report;
	}

	private String getEntropyReport() {
		String report = "<h2>Entropy from Information Theory</h2>";
		EntropyMetric entropy = getEntropyMetric();
		report += getQualitySign(entropy);
		report += "<br/>";
		report += "<table>";
		report += "<tr><td>n</td><td>" + round(entropy.get_n()) + "</td><td>"
				+ translator.i18n("Vocabulary size") + "</td></tr>";
		report += "<tr><td>N</td><td>" + round(entropy.get_N()) + "</td><td>"
				+ translator.i18n("Program length") + "</td></tr>";
		report += "<tr><td>Entropy</td><td>" + round(entropy.getEntropy())
				+ "</td><td>" + translator.i18n("entropy") + "</td></tr>";
		report += "<tr><td>maxEntropy</td><td>"
				+ round(entropy.getMaxEntropy()) + "</td><td>"
				+ translator.i18n("maximized entropy") + "</td></tr>";
		report += "<tr><td>normEntropy</td><td>"
				+ round(entropy.getNormEntropy()) + "</td><td>"
				+ translator.i18n("normalized entropy") + "</td></tr>";
		report += "<tr><td>Entropy Redundance</td><td>"
				+ round(entropy.getEntropyRedundancy()) + "</td><td>"
				+ translator.i18n("redundance in entropy") + "</td></tr>";
		report += "<tr><td>Redundance</td><td>"
				+ round(entropy.getRedundancy()) + "</td><td>"
				+ translator.i18n("number of redundand vokables")
				+ "</td></tr>";
		report += "<tr><td>normRedundancy</td><td>"
				+ round(entropy.getNormRedundancy()) + "</td><td>"
				+ translator.i18n("ratio of redundand vocables") + "</td></tr>";
		report += "</table>";
		return report;
	}

	private String getSource() {
		String report = "<h2>" + translator.i18n("Source Code") + "</h2>";
		report += "<tt>";
		report += getCodeRange().getText().replaceAll("\n", "<br/>")
				.replaceAll(" ", "&nbsp;").replaceAll("\t",
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

	@Override
	public QualityLevel getQualityLevel() {
		QualityLevel level = getSLOCMetric().getQualityLevel();
		if (level.getLevel() > getMcCabeMetric().getQualityLevel().getLevel()) {
			level = getMcCabeMetric().getQualityLevel();
		}
		if (level.getLevel() > getHalsteadMetric().getQualityLevel().getLevel()) {
			level = getHalsteadMetric().getQualityLevel();
		}
		if (level.getLevel() > getMaintainabilityIndex().getQualityLevel()
				.getLevel()) {
			level = getMaintainabilityIndex().getQualityLevel();
		}
		if (level.getLevel() > getEntropyMetric().getQualityLevel().getLevel()) {
			level = getEntropyMetric().getQualityLevel();
		}
		return level;
	}
}
