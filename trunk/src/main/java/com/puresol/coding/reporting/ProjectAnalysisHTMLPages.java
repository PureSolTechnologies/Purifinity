package com.puresol.coding.reporting;

import java.io.File;
import java.io.IOException;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.CodeRangeEvaluatorManager;
import com.puresol.document.Document;
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.Application;
import com.puresol.gui.progress.ProgressObserver;
import com.puresol.gui.progress.RunnableProgressObservable;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

public class ProjectAnalysisHTMLPages implements RunnableProgressObservable {

	private static final Logger logger = Logger
			.getLogger(ProjectAnalysisHTMLPages.class);
	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisHTMLPages.class);

	private ProgressObserver monitor = null;
	private final ProjectAnalyzer projectAnalyzer;
	private final File directory;

	public ProjectAnalysisHTMLPages(ProjectAnalyzer projectAnalyzer,
			File directory) {
		super();
		this.projectAnalyzer = projectAnalyzer;
		this.directory = directory;
	}

	public ProjectAnalyzer getProjectAnalyzer() {
		return projectAnalyzer;
	}

	public File getDirectory() {
		return directory;
	}

	@Override
	public void run() {
		try {
			if (monitor != null) {
				monitor.setDescription("Creating HTML project pages...");
				monitor.setRange(0, projectAnalyzer.getAnalyzedFiles().size());
				monitor.setStatus(0);
			}
			int count = 0;
			for (AnalyzedFile file : projectAnalyzer.getAnalyzedFiles()) {
				count++;
				if (monitor != null) {
					monitor.setStatus(count);
				}
				processFile(file);
				if (Thread.interrupted()) {
					break;
				}
			}
			if (monitor != null) {
				monitor.finish();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			Application.showStandardErrorMessage(
					translator.i18n("Could not create HTML project pages!"), e);
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			Application.showStandardErrorMessage(
					translator.i18n("Could not create HTML project pages!"), e);
		}
	}

	private void processFile(AnalyzedFile analyzedFile) throws IOException,
			PersistenceException {
		Analysis analysis = projectAnalyzer.getAnalysis(analyzedFile);
		for (CodeRangeEvaluatorFactory evaluatorFactory : CodeRangeEvaluatorManager
				.getAll()) {
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				File file = analyzedFile.getReportFile(
						evaluatorFactory.getCodeRangeEvaluatorClass(),
						codeRange);
				Document document = (Document) Persistence.restore(file);
				FileUtilities.writeFile(directory, new File(file, ".html"),
						new HTMLConverter(document).toString());
			}
		}
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.monitor = observer;
	}

}
