package com.puresol.coding.reporting.html;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.gui.Application;
import com.puresol.gui.progress.ProgressObserver;
import com.puresol.gui.progress.RunnableProgressObservable;
import com.puresol.trees.FileTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceException;

class FileAnalysisIndizes implements RunnableProgressObservable {

	private static final Logger logger = Logger
			.getLogger(CodeRangeAnalysisReports.class);
	private static final Translator translator = Translator
			.getTranslator(CodeRangeAnalysisReports.class);

	private final ProjectAnalyzer projectAnalyzer;
	private final File htmlRootDirectory;
	private final List<AnalyzedFile> files;
	private final FileTree fileTree;
	private final Map<File, AnalyzedFile> analyzedFiles = new HashMap<File, AnalyzedFile>();

	private ProgressObserver monitor = null;

	public FileAnalysisIndizes(ProjectAnalyzer projectAnalyzer,
			File rootDirectory) {
		super();
		this.projectAnalyzer = projectAnalyzer;
		this.htmlRootDirectory = rootDirectory;

		files = projectAnalyzer.getAnalyzedFiles();
		List<File> realFiles = new ArrayList<File>();
		for (AnalyzedFile analyzedFile : files) {
			realFiles.add(analyzedFile.getFile());
			analyzedFiles.put(new File(".", analyzedFile.getFile().getPath()),
					analyzedFile);
		}
		fileTree = FileUtilities.convertFileListToTree(".", realFiles);
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
			run(fileTree);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			if (monitor != null) {
				monitor.terminated(this);
			}
			Application.showStandardErrorMessage(
					translator.i18n("Could not create HTML project pages!"), e);
		}
	}

	private void run(FileTree fileTree) throws IOException,
			PersistenceException, InterruptedException {
		if (monitor != null) {
			monitor.setTitle(translator
					.i18n("Create File Indizes (index.html)..."));
			monitor.showProgressPercent();
			monitor.setRange(0, files.size() - 1);
			monitor.setStatus(0);
		}
		AnalyzedFile analyzedFile = analyzedFiles.get(fileTree.getPathFile());
		if (analyzedFile != null) {
			processFile(analyzedFile);
		} else {
			processDirectory(fileTree.getPathFile());
		}
		for (FileTree child : fileTree.getChildren()) {
			run(child);
		}
		if (monitor != null) {
			monitor.finished(this);
		}
	}

	private void processFile(final AnalyzedFile analyzedFile)
			throws IOException, PersistenceException, InterruptedException {
		File file = HTMLProjectAnalysisCreator.getIndexFile(htmlRootDirectory,
				analyzedFile);

		HTMLReport indexFile = new HTMLReport(file,
				HTMLProjectAnalysisCreator.getCSSFile(htmlRootDirectory),
				HTMLProjectAnalysisCreator.getLogoFile(htmlRootDirectory),
				HTMLProjectAnalysisCreator.getFavIconFile(htmlRootDirectory),
				translator.i18n("Analysis for File {0}", analyzedFile.getFile()
						.getName()));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile.write(MainMenu.getHTML(htmlRootDirectory, file,
					MainMenu.FILES));

			indexFile.write("<table>\n");
			indexFile.write("<tr>\n");
			indexFile.write("<td valign=\"top\">\n");

			indexFile.write(getFilesBrowser(analyzedFile.getFile()));

			Analysis analysis = projectAnalyzer.getAnalysis(analyzedFile);
			indexFile.write(getCodeRangeMenu(analyzedFile, analysis));

			indexFile.write("</td>\n");
			indexFile.write("<td>\n");

			indexFile
					.write("Some basic information about file to be inserted...");
			indexFile.write(analysis.getLanguage().toString());

			indexFile.write("</td>\n");
			indexFile.write("</tr>\n");
			indexFile.write("</table>\n");
		} finally {
			indexFile.close();
		}

	}

	private void processDirectory(File directory) throws IOException {
		File file = new File(
				HTMLProjectAnalysisCreator
						.getCodeRangeEvaluatorsDirectory(htmlRootDirectory),
				directory.getPath());
		file = new File(file, "index.html");
		file = FileUtilities.normalizePath(file);

		HTMLReport indexFile = new HTMLReport(file,
				HTMLProjectAnalysisCreator.getCSSFile(htmlRootDirectory),
				HTMLProjectAnalysisCreator.getLogoFile(htmlRootDirectory),
				HTMLProjectAnalysisCreator.getFavIconFile(htmlRootDirectory),
				translator.i18n("Directory Content"));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile.write(MainMenu.getHTML(htmlRootDirectory, file,
					MainMenu.FILES));

			indexFile.write("<table>\n");
			indexFile.write("<tr>\n");
			indexFile.write("<td valign=\"top\">\n");

			indexFile.write(getFilesBrowser(directory));

			indexFile.write("</td>\n");
			indexFile.write("<td>\n");

			indexFile.write("Some basic information about the directory...");

			indexFile.write("</td>\n");
			indexFile.write("</tr>\n");
			indexFile.write("</table>\n");
		} finally {
			indexFile.close();
		}
	}

	private String getFilesBrowser(File relativeFile) throws IOException {
		return FileBrowser.getFileBrowserHTML(htmlRootDirectory, relativeFile,
				fileTree);
	}

	private String getCodeRangeMenu(AnalyzedFile analyzedFile, Analysis analysis)
			throws IOException {
		return CodeRangeMenu.getCodeRangeMenuHTML(htmlRootDirectory,
				analyzedFile, analysis, null);
	}
}
