package com.puresol.coding.reporting.html;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.Application;
import com.puresol.gui.progress.ProgressObserver;
import com.puresol.gui.progress.ProgressPanel;
import com.puresol.gui.progress.RunnableProgressObservable;
import com.puresol.trees.FileTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceException;

class CodeRangeAnalysisReports implements RunnableProgressObservable {

	private static final Logger logger = Logger
			.getLogger(CodeRangeAnalysisReports.class);
	private static final Translator translator = Translator
			.getTranslator(CodeRangeAnalysisReports.class);

	private final ProjectAnalyzer projectAnalyzer;
	private final File htmlRootDirectory;
	private final List<AnalyzedFile> files;
	private final FileTree fileTree;
	private final List<String> usedCodeRangeNames = new ArrayList<String>();

	private ProgressObserver monitor = null;

	public CodeRangeAnalysisReports(ProjectAnalyzer projectAnalyzer,
			File htmlRootDirectory) {
		super();
		this.projectAnalyzer = projectAnalyzer;
		this.htmlRootDirectory = htmlRootDirectory;
		files = projectAnalyzer.getAnalyzedFiles();
		List<File> realFiles = new ArrayList<File>();
		for (AnalyzedFile analyzedFile : files) {
			realFiles.add(analyzedFile.getFile());
		}
		fileTree = FileUtilities.convertFileListToTree(".",
				realFiles);
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.monitor = observer;
	}

	@Override
	public ProgressObserver getMonitor() {
		return monitor;
	}

	@Override
	public synchronized void run() {
		try {
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
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			if (monitor != null) {
				monitor.terminated(this);
			}
			Application.showStandardErrorMessage(
					translator.i18n("Could not create HTML project pages!"), e);
		}
	}

	private void processFile(final AnalyzedFile analyzedFile)
			throws IOException, PersistenceException, InterruptedException {
		final Analysis analysis = projectAnalyzer.getAnalysis(analyzedFile);
		ProgressPanel panel = monitor.getSubProgressPanel();
		panel.runSyncronous(new RunnableProgressObservable() {

			private ProgressObserver monitor = null;

			@Override
			public void run() {
				try {
					List<CodeRange> codeRanges = analysis
							.getAnalyzableCodeRanges();
					monitor.setTitle(translator
							.i18n("Processing CodeRanges..."));
					monitor.setRange(0, codeRanges.size() - 1);
					monitor.showProgressPercent();
					usedCodeRangeNames.clear();
					for (int index = 0; index < codeRanges.size(); index++) {
						CodeRange codeRange = codeRanges.get(index);
						String codeRangeName = codeRange.toString();
						int dummy = 1;
						while (usedCodeRangeNames.contains(codeRangeName)) {
							dummy++;
							codeRangeName = codeRange.toString() + " " + dummy;
						}
						usedCodeRangeNames.add(codeRangeName);
						monitor.setText(codeRange.toString());
						monitor.setStatus(index);
						processFile(analyzedFile, analysis, codeRange,
								codeRangeName);
						if (Thread.interrupted()) {
							monitor.terminated(this);
							return;
						}
					}
					monitor.finished(this);
				} catch (Throwable e) {
					logger.error(e.getMessage(), e);
					monitor.terminated(this);
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
		});
	}

	private void processFile(AnalyzedFile analyzedFile, Analysis analysis,
			CodeRange codeRange, String codeRangeName) throws IOException {
		for (CodeRangeEvaluatorFactory evaluatorFactory : CodeRangeEvaluatorManager
				.getAll()) {
			processFile(analyzedFile, analysis, evaluatorFactory, codeRange,
					codeRangeName);
		}

		File file = HTMLProjectAnalysisCreator.getFile(htmlRootDirectory, analyzedFile,
				codeRangeName);

		HTMLReport indexFile = new HTMLReport(file,
				HTMLProjectAnalysisCreator.getCSSFile(htmlRootDirectory),
				HTMLProjectAnalysisCreator.getLogoFile(htmlRootDirectory),
				HTMLProjectAnalysisCreator.getFavIconFile(htmlRootDirectory),
				translator.i18n("CodeRange Analysis for {0}", codeRange
						.getType().getName() + " " + codeRange.getName()));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile.write(MainMenu.getHTML(htmlRootDirectory, file, MainMenu.FILES));

			indexFile.write("<table>\n");
			indexFile.write("<tr>\n");
			indexFile.write("<td valign=\"top\">\n");

			indexFile.write(getFilesMenu(analyzedFile));
			indexFile.write(CodeRangeMenu.getCodeRangeMenuHTML(htmlRootDirectory,
					analyzedFile, analysis, codeRange));
			indexFile.write(EvaluatorMenu.getCodeRangeEvaluatorMenuHTML(
					htmlRootDirectory, file.getPath(), analyzedFile, codeRange, null));

			indexFile.write("</td>\n");
			indexFile.write("<td>\n");

			indexFile.write("Show code range source code here!");

			indexFile.write("</td>\n");
			indexFile.write("</tr>\n");
			indexFile.write("</table>\n");
		} finally {
			indexFile.close();
		}
	}

	private void processFile(AnalyzedFile analyzedFile, Analysis analysis,
			CodeRangeEvaluatorFactory evaluatorFactory, CodeRange codeRange,
			String codeRangeName) throws IOException {
		CodeRangeEvaluator evaluator = evaluatorFactory.create(
				analysis.getLanguage(), codeRange);
		evaluator.run();

		File file = HTMLProjectAnalysisCreator.getFile(htmlRootDirectory, analyzedFile,
				codeRange, evaluatorFactory);

		HTMLReport indexFile = new HTMLReport(file,
				HTMLProjectAnalysisCreator.getCSSFile(htmlRootDirectory),
				HTMLProjectAnalysisCreator.getLogoFile(htmlRootDirectory),
				HTMLProjectAnalysisCreator.getFavIconFile(htmlRootDirectory),
				translator.i18n("CodeRange Analysis for {0}", codeRange
						.getType().getName() + " " + codeRange.getName()));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile.write(MainMenu.getHTML(htmlRootDirectory, file, MainMenu.FILES));

			indexFile.write("<table>\n");
			indexFile.write("<tr>\n");
			indexFile.write("<td valign=\"top\">\n");

			indexFile.write(getFilesMenu(analyzedFile));
			indexFile.write(CodeRangeMenu.getCodeRangeMenuHTML(htmlRootDirectory,
					analyzedFile, analysis, codeRange));
			indexFile.write(EvaluatorMenu.getCodeRangeEvaluatorMenuHTML(
					htmlRootDirectory, file.getPath(), analyzedFile, codeRange,
					evaluatorFactory));

			indexFile.write("</td>\n");
			indexFile.write("<td>\n");

			indexFile.write(new HTMLConverter(evaluator.getReport(), indexFile
					.getFile().getParentFile()).toHTML(false));

			indexFile.write("</td>\n");
			indexFile.write("</tr>\n");
			indexFile.write("</table>\n");
		} finally {
			indexFile.close();
		}
	}

	private String getFilesMenu(AnalyzedFile analyzedFile) throws IOException {
		return FileBrowser.getFileBrowserHTML(htmlRootDirectory,
				analyzedFile.getFile(), fileTree);
	}
}
