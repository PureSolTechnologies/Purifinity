/***************************************************************************
 *
 *   MaintainabilityIndex.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.maintainability;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.evaluator.AbstractCodeRangeEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetric;
import com.puresol.coding.metrics.mccabe.McCabeMetric;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class MaintainabilityIndex extends AbstractCodeRangeEvaluator {

	private static final long serialVersionUID = 2789695185933616684L;

	private static final Translator translator = Translator
			.getTranslator(MaintainabilityIndex.class);

	public static final String NAME = translator.i18n("Maintainability Index");
	public static final String DESCRIPTION = translator
			.i18n("Maintainability Index calculation.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(MaintainabilityIndex.class,
				"enabled",
				"Switches calculation of Maintainability Index on and off.",
				Boolean.class, "true"));
	}
	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.CHANGEABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.TESTABILITY);
	}

	/**
	 * MaintainabilityIndex without comment.
	 */
	private double MIwoc;
	/**
	 * MaintainabilityIndex comment weight
	 */
	private double MIcw;
	/**
	 * MaintainabilityIndex
	 */
	private double MI;

	private final CodeRange codeRange;
	private SLOCMetric slocMetric;
	private McCabeMetric mcCabeMetric;
	private HalsteadMetric halsteadMetric;

	public MaintainabilityIndex(CodeRange codeRange) {
		super(codeRange);
		this.codeRange = codeRange;
	}

	private void checkInput() {
		if (codeRange != slocMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
		if (codeRange != mcCabeMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
		if (codeRange != halsteadMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
	}

	@Override
	public void run() {
		this.slocMetric = new SLOCMetric(codeRange);
		this.mcCabeMetric = new McCabeMetric(codeRange);
		this.halsteadMetric = new HalsteadMetric(codeRange);
		checkInput();
		MIwoc = 171.0 - 5.2 * Math.log(halsteadMetric.get_HV()) - 0.23
				* mcCabeMetric.getCyclomaticNumber() - 16.2
				* Math.log(slocMetric.getPhyLOC() * 100.0 / 171.0);
		MIcw = 50 * Math.sin(Math.sqrt(2.4 * (double) slocMetric.getComLOC()
				/ (double) slocMetric.getPhyLOC()));
		MI = MIwoc + MIcw;
	}

	public void print() {
		System.out.println("MIwoc = " + MIwoc);
		System.out.println("MIcw = " + MIcw);
		System.out.println("MI = " + MI);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.MaintainabilityIndex#getMIWoc()
	 */
	public double getMIWoc() {
		return MIwoc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.MaintainabilityIndex#getMIcw()
	 */
	public double getMIcw() {
		return MIcw;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.MaintainabilityIndex#getMI()
	 */
	public double getMI() {
		return MI;
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	@Override
	public QualityLevel getQuality() {
		CodeRange range = getCodeRange();
		if ((range.getCodeRangeType() == CodeRangeType.FILE)
				|| (range.getCodeRangeType() == CodeRangeType.CLASS)
				|| (range.getCodeRangeType() == CodeRangeType.ENUMERATION)) {
			if (getMI() > 86) {
				return QualityLevel.HIGH;
			}
			if (getMI() > 65) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		} else if ((range.getCodeRangeType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getCodeRangeType() == CodeRangeType.METHOD)
				|| (range.getCodeRangeType() == CodeRangeType.FUNCTION)
				|| (range.getCodeRangeType() == CodeRangeType.INTERFACE)) {
			if (getMI() > 85) {
				return QualityLevel.HIGH;
			}
			if (getMI() > 65) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		}
		return QualityLevel.HIGH; // not evaluated...
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return DESCRIPTION;
	}

	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextReport();
		} else if (format == ReportingFormat.HTML) {
			return getHTMLReport();
		} else {
			throw new UnsupportedFormatException(format);
		}
	}

	public String getTextReport() {
		String report = "MIwoc\t" + Math.round(getMIWoc() * 100.0) / 100.0
				+ "\t"
				+ translator.i18n("Maintainability index without comments")
				+ "\n";
		report += "MIcw\t" + Math.round(getMIcw() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Maintainability index comment weight")
				+ "\n";
		report += "MI\t" + Math.round(getMI() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Maintainability index") + "\n";
		return report;
	}

	public String getHTMLReport() {
		String report = Anchor.generate(getName(),
				"<h3>" + translator.i18n("Maintainability Index") + "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += HTMLStandards.convertTSVToTable(getTextReport());
		return report;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
