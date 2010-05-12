package com.puresol.coding.lang.java.evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.lang.java.Java;
import com.puresol.coding.lang.java.source.grammar.classes.ClassDeclaration;
import com.puresol.coding.lang.java.source.grammar.classes.VariableDeclarator;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class TranslatorImplementation extends AbstractEvaluator {

	private static final long serialVersionUID = 7618097301047375380L;

	private static final Translator translator = Translator
			.getTranslator(TranslatorImplementation.class);

	public static final String NAME = "Translator Implementation for Java";
	public static final String DESCRIPTION = translator
			.i18n("This evaluator checks for a Translator implementation"
					+ "recommended by PureSol-Technologies."
					+ "Recommendation: \"private static final Translator translator = Translator.getTranslator(<class>);\"");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	private static Map<CodeRange, QualityLevel> levels = new Hashtable<CodeRange, QualityLevel>();
	private static Map<CodeRange, VariableDeclarator> fields = new Hashtable<CodeRange, VariableDeclarator>();

	public TranslatorImplementation(ProjectAnalyser analyser) {
		super(analyser);
	}

	@Override
	public void run() {
		ProgressObserver observer = getMonitor();
		if (observer != null) {
			observer.setDescription(NAME);
			observer.setRange(0, getProjectAnalyser().getFiles().size());
			observer.setStatus(0);
		}
		int count = 0;
		for (File file : getProjectAnalyser().getFiles()) {
			if (Thread.interrupted()) {
				return;
			}
			count++;
			if (observer != null) {
				observer.setStatus(count);
			}
			analyse(file);
		}
		if (observer != null) {
			observer.finish();
		}
	}

	private void analyse(File file) {
		Analyser analyser = getProjectAnalyser().getAnalyser(file);
		for (CodeRange codeRange : analyser.getNamedCodeRanges()) {
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
		for (VariableDeclarator field : classDeclaration.getFields()) {
			if (!(field.getVariableType().equals(
					Translator.class.getSimpleName()) || field
					.getVariableType().equals(Translator.class.getName()))) {
				continue;
			}
			addFile(codeRange.getFile());
			addCodeRange(codeRange);
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
			if (!field.getName().equals("translator")) {
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
	public String getProjectComment(ReportingFormat format) {
		return "";
	}

	@Override
	public QualityLevel getProjectQuality() {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) throws UnsupportedReportingFormatException {
		QualityLevel qualityLevel = levels.get(codeRange);
		if (qualityLevel == null) {
			return "";
		}
		if (format == ReportingFormat.TEXT) {
			String text = translator.i18n("Quality Level:") + " "
					+ qualityLevel.getIdentifier();
			text += "\n";
			text += fields.get(codeRange).toString(format);
			text += "\n";
			text += codeRange.toString(format);
			return text;
		} else if (format == ReportingFormat.HTML) {
			String text = translator.i18n("Quality Level:") + " "
					+ HTMLConverter.convertQualityLevelToHTML(qualityLevel);
			text += "<br/>";
			text += fields.get(codeRange).toString(format);
			text += "<br/>";
			text += codeRange.toString(format);
			return text;
		}
		throw new UnsupportedReportingFormatException(format);
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		QualityLevel qualityLevel = levels.get(codeRange);
		if (qualityLevel == null) {
			return QualityLevel.UNSPECIFIED;
		}
		return qualityLevel;
	}

}
