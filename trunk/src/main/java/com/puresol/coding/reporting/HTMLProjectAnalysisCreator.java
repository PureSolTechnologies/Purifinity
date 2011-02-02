package com.puresol.coding.reporting;

import java.io.File;
import java.io.IOException;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.Application;
import com.puresol.gui.progress.ProgressObserver;
import com.puresol.gui.progress.RunnableProgressObservable;
import com.puresol.utils.JARUtilities;

/**
 * This class generated HTML linked pages about all results of a project
 * analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HTMLProjectAnalysisCreator implements RunnableProgressObservable {

	private static final Logger logger = Logger
			.getLogger(HTMLProjectAnalysisCreator.class);
	private static final Translator translator = Translator
			.getTranslator(HTMLProjectAnalysisCreator.class);

	public static File getCSSDirectory(File directory) {
		return new File(directory, "css");
	}

	public static File getGraphicsDirectory(File directory) {
		return new File(directory, "graphics");
	}

	public static File getProjectEvaluatorsDirectory(File directory) {
		return new File(directory, "project");
	}

	public static File getCodeRangeEvaluatorsDirectory(File directory) {
		return new File(directory, "files");
	}

	public static File getLogoFile(File directory) {
		return new File(getGraphicsDirectory(directory),
				"puresol-technologies.png");
	}

	public static File getFavIconFile(File directory) {
		return new File(getGraphicsDirectory(directory), "favicon.png");
	}

	public static File getCSSFile(File directory) {
		return new File(getCSSDirectory(directory), "analysis-report.css");
	}

	private ProgressObserver monitor = null;
	private final ProjectAnalyzer projectAnalyzer;
	private final File directory;

	public HTMLProjectAnalysisCreator(ProjectAnalyzer projectAnalyzer,
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

	public File getCSSDirectory() {
		return getCSSDirectory(directory);
	}

	public File getGraphicsDirectory() {
		return getGraphicsDirectory(directory);
	}

	public File getProjectEvaluatorsDirectory() {
		return getProjectEvaluatorsDirectory(directory);
	}

	public File getCodeRangeEvaluatorsDirectory() {
		return getCodeRangeEvaluatorsDirectory(directory);
	}

	public File getLogoFile() {
		return getLogoFile(directory);
	}

	public File getFavIconFile() {
		return getFavIconFile(directory);
	}

	public File getCSSFile() {
		return getCSSFile(directory);
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
			createCodeRangeAnalysisReports();
			count++;
			if (monitor != null) {
				monitor.setStatus(count);
			}
			createProjectEvaluatorReports();
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

	private void createProjectEvaluatorReports() throws InterruptedException {
		monitor.getSubProgressPanel().runSyncronous(
				new ProjectAnalysisReports(projectAnalyzer, directory));
	}

	private void createCodeRangeAnalysisReports() throws InterruptedException {
		monitor.getSubProgressPanel().runSyncronous(
				new CodeRangeAnalysisReports(projectAnalyzer, directory));
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

	private void createTopIndexHTML() throws IOException {
		File file = new File(directory, "index.html");
		HTMLReport indexFile = new HTMLReport(file, getCSSFile(),
				getLogoFile(), getFavIconFile(),
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

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.monitor = observer;
	}

	@Override
	public ProgressObserver getMonitor() {
		return monitor;
	}

}
