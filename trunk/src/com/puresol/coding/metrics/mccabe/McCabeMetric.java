/***************************************************************************
 *
 *   McCabeMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.mccabe;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractCodeRangeEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.Property;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends AbstractCodeRangeEvaluator {

	private static final long serialVersionUID = 4402746003873908301L;

	private static final Translator translator = Translator
			.getTranslator(McCabeMetric.class);

	public static final String NAME = translator.i18n("McCabe Metric");

	public static final String DESCRIPTION = translator
			.i18n("McCabe Metric calculation.");

	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(McCabeMetric.class, "enabled",
				"Switches calculation of McCabe Metric on and off.",
				Boolean.class, "true"));
	}

	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.TESTABILITY);
	}

	private int cyclomaticNumber = 1;
	private final LanguageDependedMcCabeMetric langDepended;

	public McCabeMetric(ProgrammingLanguage language, CodeRange codeRange) {
		super(codeRange);
		langDepended = language
				.getImplementation(LanguageDependedMcCabeMetric.class);
		if (langDepended == null) {
			throw new RuntimeException();
		}
	}

	@Override
	public void run() {
		if (getMonitor() != null) {
			getMonitor().setRange(0, 1);
			getMonitor().setDescription(NAME);
		}
		cyclomaticNumber = 1;
		TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
				getCodeRange().getParserTree());
		do {
			if (langDepended.increasesCyclomaticComplexity(iterator
					.getCurrentNode())) {
				cyclomaticNumber++;
			}
		} while (iterator.goForward());
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.McCabeMetric#getCyclomaticNumber()
	 */
	public int getCyclomaticNumber() {
		return cyclomaticNumber;
	}

	public void print() {
		System.out.println("v(G) = " + cyclomaticNumber);
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	@Override
	public QualityLevel getQuality() {
		CodeRange range = getCodeRange();
		if ((range.getType() == CodeRangeType.FILE)
				|| (range.getType() == CodeRangeType.CLASS)
				|| (range.getType() == CodeRangeType.ENUMERATION)) {
			if (getCyclomaticNumber() < 100) {
				return QualityLevel.HIGH;
			}
			if (getCyclomaticNumber() < 125) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getType() == CodeRangeType.METHOD)
				|| (range.getType() == CodeRangeType.FUNCTION)
				|| (range.getType() == CodeRangeType.INTERFACE)) {
			if (getCyclomaticNumber() < 15) {
				return QualityLevel.HIGH;
			}
			if (getCyclomaticNumber() < 20) {
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
		if (format == ReportingFormat.HTML) {
			return getHTMLReport();
		} else {
			throw new UnsupportedFormatException(format);
		}
	}

	public String getHTMLReport() {
		String report = Anchor.generate(getName(),
				"<h3>" + translator.i18n("McCabe Cyclomatic Number") + "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += translator.i18n("Cyclomatic number v(G)") + "="
				+ getCyclomaticNumber();
		return report;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
