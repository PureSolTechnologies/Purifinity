package com.puresol.coding.reporting;

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
import com.puresol.document.Document;
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.Application;
import com.puresol.gui.progress.ProgressObserver;
import com.puresol.gui.progress.ProgressPanel;
import com.puresol.gui.progress.RunnableProgressObservable;
import com.puresol.trees.FileTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceException;

public class CodeRangeAnalysisReports implements RunnableProgressObservable {

	private static final Logger logger = Logger
			.getLogger(CodeRangeAnalysisReports.class);
	private static final Translator translator = Translator
			.getTranslator(CodeRangeAnalysisReports.class);

	private final ProjectAnalyzer projectAnalyzer;
	private final File directory;
	private final List<AnalyzedFile> files;
	private final FileTree fileTree;

	private ProgressObserver monitor = null;

	public CodeRangeAnalysisReports(ProjectAnalyzer projectAnalyzer,
			File directory) {
		super();
		this.projectAnalyzer = projectAnalyzer;
		this.directory = directory;
		files = projectAnalyzer.getAnalyzedFiles();
		List<File> realFiles = new ArrayList<File>();
		for (AnalyzedFile analyzedFile : files) {
			realFiles.add(analyzedFile.getFile());
		}
		fileTree = FileUtilities.convertFileListToTree(directory.getPath(),
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
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			Application.showStandardErrorMessage(
					translator.i18n("Could not create HTML project pages!"), e);
			if (monitor != null) {
				monitor.terminated(this);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			Application.showStandardErrorMessage(
					translator.i18n("Could not create HTML project pages!"), e);
			if (monitor != null) {
				monitor.terminated(this);
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			Application
					.showStandardErrorMessage(
							translator
									.i18n("Could not create HTML project pages due to termination!"),
							e);
			if (monitor != null) {
				monitor.terminated(this);
			}
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
					for (int index = 0; index < codeRanges.size(); index++) {
						CodeRange codeRange = codeRanges.get(index);
						monitor.setText(codeRange.toString());
						monitor.setStatus(index);
						processFile(analyzedFile, analysis, codeRange);
						if (Thread.interrupted()) {
							monitor.terminated(this);
							return;
						}
					}
					monitor.finished(this);
				} catch (IOException e) {
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
			CodeRange codeRange) throws IOException {
		for (CodeRangeEvaluatorFactory evaluatorFactory : CodeRangeEvaluatorManager
				.getAll()) {
			processFile(analyzedFile, analysis, evaluatorFactory, codeRange);
		}
	}

	private void processFile(AnalyzedFile analyzedFile, Analysis analysis,
			CodeRangeEvaluatorFactory evaluatorFactory, CodeRange codeRange)
			throws IOException {
		final File codeRangeEvaluatorsDirectory = HTMLProjectAnalysisCreator
				.getCodeRangeEvaluatorsDirectory(directory);
		CodeRangeEvaluator evaluator = evaluatorFactory.create(
				analysis.getLanguage(), codeRange);
		evaluator.run();
		Document document = evaluator.getReport();
		FileUtilities.writeFile(
				codeRangeEvaluatorsDirectory,
				new File(analyzedFile.getFile(), evaluator.getName() + "-"
						+ codeRange.getType().getName() + "-"
						+ codeRange.getName() + ".html"), new HTMLConverter(
						document, analyzedFile.getFile()).toHTML(true));

		File file = new File(codeRangeEvaluatorsDirectory, analyzedFile
				.getFile().getPath());
		file = new File(file, evaluator.getName() + "-"
				+ codeRange.getType().getName() + "-" + codeRange.getName()
				+ ".html");

		HTMLReport indexFile = new HTMLReport(file,
				HTMLProjectAnalysisCreator.getCSSFile(directory),
				HTMLProjectAnalysisCreator.getLogoFile(directory),
				HTMLProjectAnalysisCreator.getFavIconFile(directory),
				translator.i18n("CodeRange Analysis for {0}", codeRange
						.getType().getName() + " " + codeRange.getName()));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile.write(MainMenu.getHTML(directory, file, MainMenu.FILES));

			indexFile.write("<table>\n");
			indexFile.write("<tr>\n");
			indexFile.write("<td valign=\"top\">\n");

			indexFile.write(getFilesMenu(analyzedFile));

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

	private String getFilesMenu(AnalyzedFile analyzedFile) {
		String pathParts[] = analyzedFile.getFile().getPath()
				.split(File.separator);
		FileTree currentNode = fileTree;
		return getFileBrowserHTML(analyzedFile.getFile(), pathParts, 1,
				currentNode);
	}

	private String getFileBrowserHTML(File file, String[] pathParts, int i,
			FileTree currentNode) {
		if (i == pathParts.length) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("<ul>\n");
		for (FileTree node : currentNode.getChildren()) {
			if ((!node.hasChildren()) && (node.getName().equals(pathParts[i]))) {
				builder.append("<li><b>");
				builder.append(node.getName());
				builder.append("</b></li>\n");
			} else {
				if (node.getName().equals(pathParts[i])) {
					builder.append("<li><b>");
					if (node.hasChildren()) {
						builder.append("-");
						builder.append(createLink(file, node));
						builder.append(File.separator);
					} else {
						builder.append(createLink(file, node));
					}
					builder.append("</b></li>");
					builder.append(getFileBrowserHTML(file, pathParts, i + 1,
							node));
				} else {
					builder.append("<li><b>");
					if (node.hasChildren()) {
						builder.append("+");
						builder.append(createLink(file, node));
						builder.append(File.separator);
					} else {
						builder.append(createLink(file, node));
					}
					builder.append("</b></li>");
				}
			}
		}
		builder.append("</ul>\n");
		return builder.toString();
	}

	private Object createLink(File file, FileTree node) {
		StringBuilder builder = new StringBuilder();
		builder.append("<a href=\"");
		File current = new File(
				HTMLProjectAnalysisCreator
						.getCodeRangeEvaluatorsDirectory(directory),
				file.getPath());
		File target = new File(node.getPath());
		File relativePath = FileUtilities.getRelativePath(current, target);
		builder.append(new File(relativePath, "index.html"));
		builder.append("\">");
		builder.append(node.getName());
		builder.append("</a>");
		return builder.toString();
	}
}
