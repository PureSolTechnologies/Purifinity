package com.puresol.coding.lang.java.evaluator;

import java.io.File;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;

public class TranslatorImplementation extends AbstractEvaluator {

	private static final Translator translator = Translator
			.getTranslator(TranslatorImplementation.class);

	public TranslatorImplementation(ProjectAnalyser analyser) {
		super(analyser);
	}

	@Override
	public String getName() {
		return "Translator Implementation";
	}

	@Override
	public String getDescription() {
		return translator
				.i18n("This evaluator checks for a Translator implementation"
						+ "recommended by PureSol-Technologies.");
	}

	@Override
	public QualityLevel getQuality(File file) {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public String getFileComment(File file) {
		return "";
	}

	@Override
	public String getProjectComment() {
		return "";
	}

	@Override
	public QualityLevel getProjectQuality() {
		return QualityLevel.UNSPECIFIED;
	}

	@Override
	public void run() {
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange) {
		return "";
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		return QualityLevel.UNSPECIFIED;
	}

}
