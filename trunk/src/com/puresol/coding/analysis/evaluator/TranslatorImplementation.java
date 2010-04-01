package com.puresol.coding.analysis.evaluator;

import java.io.File;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.analysis.QualityLevel;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileEvaluationComment(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectEvaluationComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QualityLevel getProjectQuality() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

}
