package com.puresol.coding.reporting;

import java.io.File;
import java.io.IOException;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.CodeRangeEvaluatorManager;
import com.puresol.document.Document;
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.Application;
import com.puresol.gui.progress.ProgressObserver;
import com.puresol.gui.progress.RunnableProgressObservable;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.JARUtilities;
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
				monitor.setRange(0,
						projectAnalyzer.getAnalyzedFiles().size() + 2);
				monitor.setStatus(0);
			}
			int count = 0;
			createTopDirectories();
			count++;
			if (monitor != null) {
				monitor.setStatus(count);
			}
			copyStandardFiles();
			count++;
			if (monitor != null) {
				monitor.setStatus(count);
			}
			createTopIndexHTML();
			count++;
			if (monitor != null) {
				monitor.setStatus(count);
			}
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

	private void createTopDirectories() throws IOException {
		createTopDirectories(getCSSDirectory());
		createTopDirectories(getGraphicsDirectory());
		createTopDirectories(getProjectEvaluatorsDirectory());
		createTopDirectories(getCodeRangeEvaluatorsDirectory());
	}

	private void createTopDirectories(File directory) throws IOException {
		if (directory.exists() && directory.isDirectory()) {
			return;
		}
		if (!directory.mkdirs()) {
			throw new IOException("Count not create Directory '"
					+ directory.getPath() + "'!");
		}
	}

	private File getCSSDirectory() {
		return new File(directory, "css");
	}

	private File getGraphicsDirectory() {
		return new File(directory, "graphics");
	}

	private File getProjectEvaluatorsDirectory() {
		return new File(directory, "project");
	}

	private File getCodeRangeEvaluatorsDirectory() {
		return new File(directory, "files");
	}

	private void copyStandardFiles() throws IOException {
		JARUtilities.copyResource(getClass().getResource("/graphics/logo.png"),
				getLogoFile());
		JARUtilities.copyResource(
				getClass().getResource("/graphics/favicon.png"),
				getFavIconFile());
		JARUtilities.copyResource(
				getClass().getResource("/css/analysis-report.css"),
				getCSSFile());
	}

	private File getLogoFile() {
		return new File(getGraphicsDirectory(), "puresol-technologies.png");
	}

	private File getFavIconFile() {
		return new File(getGraphicsDirectory(), "favicon.png");
	}

	private File getCSSFile() {
		return new File(getCSSDirectory(), "analysis-report.css");
	}

	private void createTopIndexHTML() throws IOException {
		File file = new File(directory, "index.html");
		HTMLAnalysisReport indexFile = new HTMLAnalysisReport(file,
				getCSSFile(), getLogoFile(), getFavIconFile(),
				translator.i18n("Project Analysis"));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile.write(MainMenu.getHTML(directory, file, MainMenu.START));
			indexFile.write(new HTMLConverter(projectAnalyzer.getReport())
					.toHTML(false));
		} finally {
			indexFile.close();
		}
	}

	private void processFile(AnalyzedFile analyzedFile) throws IOException,
			PersistenceException {
		Analysis analysis = projectAnalyzer.getAnalysis(analyzedFile);
		final File codeRangeEvaluatorsDirectory = getCodeRangeEvaluatorsDirectory();
		for (CodeRangeEvaluatorFactory evaluatorFactory : CodeRangeEvaluatorManager
				.getAll()) {
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				CodeRangeEvaluator evaluator = evaluatorFactory.create(
						analysis.getLanguage(), codeRange);
				evaluator.run();
				Document document = evaluator.getReport();
				FileUtilities.writeFile(codeRangeEvaluatorsDirectory, new File(
						analyzedFile.getFile(), evaluator.getName() + "-"
								+ codeRange.getType().getName() + "-"
								+ codeRange.getName() + ".html"),
						new HTMLConverter(document).toHTML(true));
			}
		}
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.monitor = observer;
	}

}
