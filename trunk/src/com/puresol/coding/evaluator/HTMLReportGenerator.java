package com.puresol.coding.evaluator;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.reporting.StandardReport;
import com.puresol.reporting.ReportingFormat;
import com.puresol.utils.Files;

public class HTMLReportGenerator extends AbstractReportGenerator {

	private static final Logger logger = Logger
			.getLogger(HTMLReportGenerator.class);
	private static final Translator translator = Translator
			.getTranslator(HTMLReportGenerator.class);

	private final StandardReport report;

	private final Hashtable<File, String> files = new Hashtable<File, String>();
	private final Hashtable<Evaluator, String> evaluators = new Hashtable<Evaluator, String>();

	public HTMLReportGenerator(CodeEvaluator codeEvaluator, File outputDirectory) {
		super(codeEvaluator);
		report = new StandardReport(outputDirectory);
		report.setCopyrightFooter(true);
		report.setCssFile(new File("css/report.css"));
		report.setLogoFile(new File("graphics/logo.jpeg"));
	}

	@Override
	public void createProject(File outputDirectory) {
		createStandardFiles();
		createProjectReport();
		createEvaluatorReports();
		createFileReports();
		createFilesList();
		createEvaluatorsList();
	}

	private void createStandardFiles() {
		report.createStandardFiles();
	}

	private void createProjectReport() {
		CodeEvaluator evaluator = getCodeEvaluator();
		String title = translator.i18n("Project Evaluation Summary");
		String text = "<h2>" + translator.i18n("Analysis Overview") + "</h2>";
		text += "<p>"
				+ translator.i18n("{0} files were analysed", evaluator
						.getFiles().size()) + "</p>";
		text += "<p>"
				+ translator
						.i18n(
								"{0} files were skipped due to parsing errors or unsupported language.",
								evaluator.getFailedFiles().size()) + "</p>";
		createProjectReport(title, text);
	}

	private void createProjectReport(String title, String text) {
		try {
			report.createFile(new File("summary.html"), title, text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void createEvaluatorReports() {
		for (Evaluator evaluator : getCodeEvaluator().getEvaluators()) {
			createEvaluatorReport(evaluator);
		}
	}

	private void createEvaluatorReport(Evaluator evaluator) {
		String title = translator.i18n("Summary for") + " "
				+ evaluator.getName();
		evaluators.put(evaluator, title);
		String text = "<h2>" + translator.i18n("Description") + "</h2>";
		text += evaluator.getDescription(ReportingFormat.HTML);
		text += "<h2>" + translator.i18n("Results") + "</h2>";
		text += evaluator.getProjectComment(ReportingFormat.HTML);
		createEvaluatorReport(evaluator, title, text);
	}

	private void createEvaluatorReport(Evaluator evaluator, String title,
			String text) {
		try {
			report.createFile(generateFile(evaluator), title, text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private File generateFile(Evaluator evaluator) {
		return Files.addPaths(new File("evaluators"), new File(evaluator
				.getClass().getName().replaceAll("\\.", "_")
				+ ".html"));
	}

	private void createFileReports() {
		CodeEvaluator evaluator = getCodeEvaluator();
		for (File file : evaluator.getFiles()) {
			createFileReport(file);
		}
	}

	private void createFileReport(File file) {
		String title = translator.i18n("Summary for File") + " '" + file + "'";
		files.put(file, title);
		String text = "";
		for (Evaluator evaluator : getCodeEvaluator().getEvaluators()) {
			String comment = evaluator.getFileComment(file,
					ReportingFormat.HTML);
			if (!comment.isEmpty()) {
				text += "<h2>" + evaluator.getName() + "</h2>";
				text += "<h3>" + translator.i18n("Description") + "</h3>";
				text += evaluator.getDescription(ReportingFormat.HTML);
				text += "<h3>" + translator.i18n("Results for") + " '"
						+ evaluator.getName() + "'</h3>";
				text += comment;
			}
		}
		createFileReport(file, title, text);
	}

	private void createFileReport(File file, String title, String text) {
		try {
			report.createFile(generateFile(file), title, text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private File generateFile(File file) {
		String fileName = file.getPath();
		if (fileName.startsWith("/")) {
			fileName.replaceFirst("/", "");
		}
		fileName = fileName.replaceAll("/", "_").replaceAll("\\.", "_")
				+ ".html";
		if (fileName.startsWith("_")) {
			fileName = fileName.replaceFirst("_", "");
		}
		return Files.addPaths(new File("files"), new File(fileName));
	}

	private void createFilesList() {
		String html = "<html><body><ul>";
		for (File file : files.keySet()) {
			html += "<li><a href=\"" + file + "\" target=\"viewer\">"
					+ files.get(file) + "</a></li>";
		}
		html += "</ul></body></html>";
		try {
			report.createFile(new File("files.html"), html);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void createEvaluatorsList() {
		String html = "<html><body><ul>";
		for (Evaluator evaluator : evaluators.keySet()) {
			html += "<li><a href=\"" + evaluator + "\" target=\"viewer\">"
					+ evaluators.get(evaluator) + "</a></li>";
		}
		html += "</ul></body></html>";
		try {
			report.createFile(new File("evaluators.html"), html);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
