package com.puresol.coding.lang.java.evaluator;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.AbstractFileEvaluator;
import com.puresol.coding.lang.java.Java;
import com.puresol.coding.lang.java.source.grammar.classes.ClassDeclaration;
import com.puresol.coding.lang.java.source.grammar.classes.FieldDeclaration;
import com.puresol.coding.lang.java.source.grammar.classes.VariableDeclarator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class TranslatorImplementation extends AbstractFileEvaluator {

	private static final long serialVersionUID = 7618097301047375380L;

	private static final Translator translator = Translator
			.getTranslator(TranslatorImplementation.class);

	public static final String NAME = "Translator Implementation for Java";
	public static final String DESCRIPTION = translator
			.i18n("This evaluator checks for a Translator implementation"
					+ "recommended by PureSol-Technologies."
					+ "Recommendation: \"private static final Translator translator = Translator.getTranslator(<class>);\"");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS.add(QualityCharacteristic.ACCURACY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.UNDERSTANDABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.TESTABILITY);
	}

	private static Map<CodeRange, QualityLevel> levels = new Hashtable<CodeRange, QualityLevel>();
	private static Map<CodeRange, FieldDeclaration> fields = new Hashtable<CodeRange, FieldDeclaration>();

	public TranslatorImplementation(Analyzer analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		Analyzer analyser = getAnalyzer();
		for (CodeRange codeRange : analyser
				.getNonFragmentCodeRangesRecursively()) {
			if (Thread.interrupted()) {
				return;
			}
			if (codeRange.getLanguage() != Java.getInstance()) {
				continue;
			}
			if (!codeRange.getClass().equals(ClassDeclaration.class)) {
				continue;
			}
			analyse(codeRange);
		}
	}

	private void analyse(CodeRange codeRange) {
		ClassDeclaration classDeclaration = (ClassDeclaration) codeRange;
		for (FieldDeclaration field : classDeclaration.getFields()) {
			if (!(field.getVariableType().equals(
					Translator.class.getSimpleName()) || field
					.getVariableType().equals(Translator.class.getName()))) {
				continue;
			}
			fields.put(codeRange, field);
			List<String> modifiers = field.getModifiers();
			if (modifiers.size() != 3) {
				levels.put(codeRange, QualityLevel.LOW);
				continue;
			}
			if ((!modifiers.contains("private"))
					|| (!modifiers.contains("static"))
					|| (!modifiers.contains("final"))) {
				levels.put(codeRange, QualityLevel.LOW);
				continue;
			}
			if (!(modifiers.get(0).equals("private")
					&& modifiers.get(1).equals("static") && modifiers.get(2)
					.equals("final"))) {
				levels.put(codeRange, QualityLevel.MEDIUM);
				continue;
			}
			List<VariableDeclarator> declarators = field
					.getChildCodeRanges(VariableDeclarator.class);
			if (declarators.size() != 1) {
				levels.put(codeRange, QualityLevel.LOW);
				continue;
			}
			if (!declarators.get(0).getName().equals("translator")) {
				levels.put(codeRange, QualityLevel.MEDIUM);
				continue;
			}
			levels.put(codeRange, QualityLevel.HIGH);
			continue;
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format) {
		if (format == ReportingFormat.HTML) {
			return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
		}
		return DESCRIPTION;
	}

	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		StringBuffer buffer = new StringBuffer();
		for (CodeRange codeRange : levels.keySet()) {
			buffer.append(getReport(codeRange, format));
		}
		return buffer.toString();
	}

	@Override
	public QualityLevel getQuality() {
		QualityLevel level = QualityLevel.HIGH;
		for (CodeRange codeRange : levels.keySet()) {
			level = QualityLevel.getMinLevel(level, getQuality(codeRange));
		}
		return level;
	}

	public String getReport(CodeRange codeRange, ReportingFormat format)
			throws UnsupportedFormatException {
		QualityLevel qualityLevel = levels.get(codeRange);
		if (qualityLevel == null) {
			return "";
		}
		if (format == ReportingFormat.TEXT) {
			String text = translator.i18n("Quality Level:") + " "
					+ qualityLevel.getIdentifier();
			text += "\n";
			text += fields.get(codeRange).getReportingString(format);
			text += "\n";
			text += codeRange.getReportingString(format);
			return text;
		} else if (format == ReportingFormat.HTML) {
			String text = translator.i18n("Quality Level:") + " "
					+ HTMLConverter.convertQualityLevelToHTML(qualityLevel);
			text += "<br/>";
			text += fields.get(codeRange).getReportingString(format);
			text += "<br/>";
			text += codeRange.getReportingString(format);
			return text;
		}
		throw new UnsupportedFormatException(format);
	}

	public QualityLevel getQuality(CodeRange codeRange) {
		QualityLevel qualityLevel = levels.get(codeRange);
		if (qualityLevel == null) {
			return QualityLevel.UNSPECIFIED;
		}
		return qualityLevel;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
