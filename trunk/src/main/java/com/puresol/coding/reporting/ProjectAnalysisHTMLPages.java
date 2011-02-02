package com.puresol.coding.reporting;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.CodeRangeEvaluatorManager;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorManager;
import com.puresol.document.Document;
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.Application;
import com.puresol.gui.progress.ProgressObserver;
import com.puresol.gui.progress.ProgressPanel;
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
				monitor.setTitle("Creating HTML project pages...");
				monitor.showProgressPercent();
				monitor.setRange(0, 5);
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
			createProjectEvaluatorReports();
			count++;
			if (monitor != null) {
				monitor.setStatus(count);
			}
			createFileEvaluatorReports();
			count++;
			if (monitor != null) {
				monitor.setStatus(count);
				monitor.finished(this);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			Application.showStandardErrorMessage(
					translator.i18n("Could not create HTML project pages!"), e);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			Application
					.showStandardErrorMessage(
							translator
									.i18n("Could not create HTML project pages due to interruption!"),
							e);
		}
	}

	private synchronized void createFileEvaluatorReports()
			throws InterruptedException {
		ProgressPanel panel = monitor.getSubProgressPanel();
		panel.runSyncronous(new RunnableProgressObservable() {

			private ProgressObserver monitor = null;

			@Override
			public void setMonitor(ProgressObserver observer) {
				monitor = observer;
			}

			@Override
			public ProgressObserver getMonitor() {
				return monitor;
			}

			@Override
			public synchronized void run() {
				try {
					List<AnalyzedFile> files = projectAnalyzer
							.getAnalyzedFiles();
					if (monitor != null) {
						monitor.setTitle(translator
								.i18n("Create File Evaluation Pages..."));
						monitor.showProgressPercent();
						monitor.setRange(0, files.size() - 1);
						monitor.setStatus(0);
					}
					for (int index = 0; index < files.size(); index++) {
						AnalyzedFile file = files.get(index);
						if (monitor != null) {
							monitor.setStatus(index);
							monitor.setText(file.getFile().getPath());
						}
						processFile(file);
						if (Thread.interrupted()) {
							if (monitor != null) {
								monitor.terminated(this);
							}
							return;
						}
					}
					if (monitor != null) {
						monitor.finished(this);
					}
				} catch (PersistenceException e) {
					logger.error(e.getMessage(), e);
					Application.showStandardErrorMessage(translator
							.i18n("Could not create HTML project pages!"), e);
					if (monitor != null) {
						monitor.terminated(this);
					}
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					Application.showStandardErrorMessage(translator
							.i18n("Could not create HTML project pages!"), e);
					if (monitor != null) {
						monitor.terminated(this);
					}
				}
			}
		});
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
			indexFile.write(new HTMLConverter(projectAnalyzer.getReport(),
					indexFile.getFile().getParentFile()).toHTML(false));
		} finally {
			indexFile.close();
		}
	}

	private synchronized void createProjectEvaluatorReports()
			throws InterruptedException, IOException {
		createProjectEvaluatorIndex();
		ProgressPanel panel = monitor.getSubProgressPanel();
		panel.runSyncronous(new RunnableProgressObservable() {

			private ProgressObserver monitor = null;

			@Override
			public void setMonitor(ProgressObserver observer) {
				monitor = observer;
			}

			@Override
			public ProgressObserver getMonitor() {
				return monitor;
			}

			@Override
			public synchronized void run() {
				try {
					List<ProjectEvaluatorFactory> evaluatorFactories = ProjectEvaluatorManager
							.getAll();
					if (monitor != null) {
						monitor.setTitle(translator
								.i18n("Create Project Evaluation Pages..."));
						monitor.showProgressPercent();
						monitor.setRange(0, evaluatorFactories.size() - 1);
						monitor.setStatus(0);
					}
					for (int index = 0; index < evaluatorFactories.size(); index++) {
						ProjectEvaluatorFactory projectEvaluatorFactory = evaluatorFactories
								.get(index);
						if (monitor != null) {
							monitor.setStatus(index);
							monitor.setText(projectEvaluatorFactory.getName());
						}
						processProjectEvaluator(projectEvaluatorFactory);
						if (Thread.interrupted()) {
							if (monitor != null) {
								monitor.terminated(this);
							}
							return;
						}
					}
					if (monitor != null) {
						monitor.finished(this);
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					if (monitor != null) {
						monitor.terminated(this);
					}
				}
			}
		});
	}

	private void createProjectEvaluatorIndex() throws IOException {
		File file = new File(new File(directory, "project"), "index.html");
		HTMLAnalysisReport indexFile = new HTMLAnalysisReport(file,
				getCSSFile(), getLogoFile(), getFavIconFile(),
				translator.i18n("Project Analysis"));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile.write(MainMenu.getHTML(directory, file, MainMenu.START));
			// TODO
		} finally {
			indexFile.close();
		}
	}

	private void processProjectEvaluator(
			ProjectEvaluatorFactory projectEvaluatorFactory)
			throws InterruptedException, IOException {
		ProjectEvaluator evaluator = projectEvaluatorFactory
				.create(projectAnalyzer);
		ProgressPanel panel = monitor.getSubProgressPanel();
		panel.runSyncronous(evaluator);

		File file = new File(new File(directory, "project"), evaluator
				.getClass().getName() + ".html");
		HTMLAnalysisReport indexFile = new HTMLAnalysisReport(file,
				getCSSFile(), getLogoFile(), getFavIconFile(),
				translator.i18n("Project Analysis"));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile.write(MainMenu.getHTML(directory, file, MainMenu.START));
			indexFile.write(new HTMLConverter(evaluator.getReport(), indexFile
					.getFile().getParentFile()).toHTML(false));
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
						new HTMLConverter(document, analyzedFile.getFile())
								.toHTML(true));
			}
		}
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.monitor = observer;
	}

	@Override
	public ProgressObserver getMonitor() {
		return monitor;
	}

}
