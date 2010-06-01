package com.puresol.coding.evaluator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.reporting.StandardReport;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.Anchor;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.reporting.html.Link;
import com.puresol.reporting.html.LinkTarget;
import com.puresol.utils.FileUtilities;

public class HTMLReportGenerator extends AbstractReportGenerator {

	private static final Logger logger = Logger
			.getLogger(HTMLReportGenerator.class);
	private static final Translator translator = Translator
			.getTranslator(HTMLReportGenerator.class);

	private final StandardReport report;

	private final Hashtable<File, String> files = new Hashtable<File, String>();
	private final Hashtable<File, String> evaluators = new Hashtable<File, String>();
	private final Link overviewLink = new Link("index.html", "Overview");
	private final Link filesLink = new Link("files.html", "Files");
	private final Link evaluatorsLink = new Link("evaluators.html",
			"Evaluators");

	public HTMLReportGenerator(ProjectEvaluator codeEvaluator,
			File outputDirectory) {
		super(codeEvaluator);
		report = new StandardReport(outputDirectory);
		report.setCopyrightFooter(true);
		report.setCssFile(new File("css/report.css"));
		report.setLogoFile(new File("graphics/logo.png"));
		report.setFavIconFile(new File("graphics/favicon.png"));
	}

	@Override
	public void createProject(File outputDirectory) {
		createStandardFiles();
		createProjectReport();
		createEvaluatorReports();
		createCodeRangeReports();
		createOverview();
		createOverviewViewer();
		createEvaluatorViewer();
		createCodeRangeViewer();
	}

	private void createStandardFiles() {
		report.createStandardFiles();
		report.createStartHTML("Creator Information");
		report.createCopyrightHTML();
	}

	private void createProjectReport() {
		ProjectEvaluator evaluator = getCodeEvaluator();
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
		String text = "<h2>" + translator.i18n("Description") + "</h2>";
		try {
			text += evaluator.getDescription(ReportingFormat.HTML);
			text += "<h2>" + translator.i18n("Results") + "</h2>";
			text += evaluator.getProjectComment(ReportingFormat.HTML);
		} catch (UnsupportedReportingFormatException e) {
			text += "<p>"
					+ translator
							.i18n(
									"Evlautor does not (yet) support {0} output format.",
									ReportingFormat.HTML.getIdentifier())
					+ "</p>";
			logger.warn(e.getMessage(), e);
		}
		createEvaluatorReport(evaluator, title, text);
	}

	private void createEvaluatorReport(Evaluator evaluator, String title,
			String text) {
		try {
			File outputFile = generateFile(evaluator);
			evaluators.put(outputFile, evaluator.getName());
			report.createFile(outputFile, title, text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private File generateFile(Evaluator evaluator) {
		return FileUtilities.addPaths(new File("evaluators"), new File(evaluator
				.getClass().getName().replaceAll("\\.", "_")
				+ ".html"));
	}

	private void createCodeRangeReports() {
		ProjectEvaluator evaluator = getCodeEvaluator();
		List<File> files = evaluator.getFiles();
		Collections.sort(files);
		for (File file : files) {
			createFileReport(file);
		}
	}

	private void createFileReport(File file) {
		String title = translator.i18n("Summary for File") + " '" + file + "'";
		String text = getTableOfContents(file);
		for (Evaluator evaluator : getCodeEvaluator().getEvaluators()) {
			for (CodeRange codeRange : evaluator.getCodeRanges(file)) {
				text += createCodeRangeReport(codeRange, evaluator);
			}
		}
		createFileReport(file, title, text);
	}

	private String getTableOfContents(File file) {
		String text = "<h2>" + translator.i18n("Table of Contents") + "</h2>\n";
		text += "<ol>\n";
		for (Evaluator evaluator : getCodeEvaluator().getEvaluators()) {
			for (CodeRange codeRange : evaluator.getCodeRanges(file)) {
				try {
					if (evaluator.getCodeRangeComment(codeRange,
							ReportingFormat.HTML).isEmpty()) {
						continue;
					}
				} catch (UnsupportedReportingFormatException e) {
					continue;
				}
				text += "<li>"
						+ new Link("#" + evaluator.getName(), evaluator
								.getName(), LinkTarget.SELF).toHTML()
						+ "</li>\n";
			}
		}
		text += "</ol>\n";
		return text;
	}

	private String createCodeRangeReport(CodeRange codeRange,
			Evaluator evaluator) {
		String text = "";
		try {
			String comment = evaluator.getCodeRangeComment(codeRange,
					ReportingFormat.HTML);
			if (!comment.isEmpty()) {
				text += new Anchor(evaluator.getName(), "<h2>"
						+ evaluator.getName() + "</h2>").toHTML()
						+ "\n";
				text += "<h3>" + translator.i18n("Description") + "</h3>";
				text += evaluator.getDescription(ReportingFormat.HTML);
				text += "<h3>" + translator.i18n("Results for") + " '"
						+ evaluator.getName() + "'</h3>";
				text += comment;
			}
		} catch (UnsupportedReportingFormatException e) {
			text += "<p>"
					+ translator
							.i18n(
									"Evlautor does not (yet) support {0} output format.",
									ReportingFormat.HTML.getIdentifier())
					+ "</p>";
			logger.warn(e.getMessage(), e);
		}
		return text;
	}

	private void createFileReport(File file, String title, String text) {
		try {
			files.put(generateFile(file), file.getPath());
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
		return FileUtilities.addPaths(new File("files"), new File(fileName));
	}

	private void createOverview() {
		String text = translator
				.i18n("<p>This project pages where create automatically during a code evaluation process.</p>");
		try {
			report.createFile(new File("overview.html"), translator
					.i18n("Overview"), text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void createOverviewViewer() {
		File file = new File("index.html");
		String html = HTMLStandards.getStandardHeader("Evaluation Overview",
				FileUtilities.getRelativePath(file, report.getCssFile()), false, FileUtilities
						.getRelativePath(file, report.getFavIconFile()));
		html += HTMLStandards.getStandardPageStart(FileUtilities.getRelativePath(file,
				report.getLogoFile()), "PureSol-Technologies", null, "");
		html += getStandardMainMenu(overviewLink);
		String overviewSubMenu = HTMLStandards.getStandardSubMenu("viewer",
				null, new Link("overview.html", "Overview"), new Link(
						"summary.html", "Summary"), new Link("copyright.html",
						"Copyright Information"));
		html += HTMLStandards
				.getStandardContentPane(
						overviewSubMenu,
						"<iframe name=\"viewer\" src=\"start.html\" width=\"100%\" height=\"800\">"
								+ translator
										.i18n("You need a browser which is able to show internal frames.")
								+ "</iframe>", "");
		html += HTMLStandards.getStandardPageEnd();
		html += HTMLStandards.getStandardFooter();
		try {
			report.createFile(file, html);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void createEvaluatorViewer() {
		File file = new File("evaluators.html");
		String html = HTMLStandards.getStandardHeader("Evaluator Results",
				FileUtilities.getRelativePath(file, report.getCssFile()), false, FileUtilities
						.getRelativePath(file, report.getFavIconFile()));
		html += HTMLStandards.getStandardPageStart(FileUtilities.getRelativePath(file,
				report.getLogoFile()), "PureSol-Technologies", null, "");
		html += getStandardMainMenu(evaluatorsLink);
		html += HTMLStandards
				.getStandardContentPane(
						createEvaluatorsSubMenu(),
						"<iframe name=\"viewer\" src=\"start.html\" width=\"100%\" height=\"800\">"
								+ translator
										.i18n("You need a browser which is able to show internal frames.")
								+ "</iframe>", "");
		html += HTMLStandards.getStandardPageEnd();
		html += HTMLStandards.getStandardFooter();
		try {
			report.createFile(file, html);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void createCodeRangeViewer() {
		File file = new File("files.html");
		String html = HTMLStandards.getStandardHeader("File Results", FileUtilities
				.getRelativePath(file, report.getCssFile()), false, FileUtilities
				.getRelativePath(file, report.getFavIconFile()));
		html += HTMLStandards.getStandardPageStart(FileUtilities.getRelativePath(file,
				report.getLogoFile()), "PureSol-Technologies", null, "");
		html += getStandardMainMenu(filesLink);
		html += HTMLStandards
				.getStandardContentPane(
						createFilesSubMenu(),
						"<iframe name=\"viewer\" src=\"start.html\" width=\"100%\" height=\"800\">"
								+ translator
										.i18n("You need a browser which is able to show internal frames.")
								+ "</iframe>", "");
		html += HTMLStandards.getStandardPageEnd();
		html += HTMLStandards.getStandardFooter();
		try {
			report.createFile(file, html);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String getStandardMainMenu(Link activeLink) {
		return HTMLStandards.getStandardMainMenu(activeLink, overviewLink,
				evaluatorsLink, filesLink);
	}

	private String createFilesSubMenu() {
		ArrayList<Link> links = new ArrayList<Link>();
		List<File> fileList = new ArrayList<File>(files.keySet());
		Collections.sort(fileList);
		for (File file : fileList) {
			links.add(new Link(file.toString(), files.get(file)));
		}
		return HTMLStandards.getStandardSubMenu("viewer", null, links
				.toArray(new Link[0]));
	}

	private String createEvaluatorsSubMenu() {
		ArrayList<Link> links = new ArrayList<Link>();
		for (File file : evaluators.keySet()) {
			links.add(new Link(file.toString(), evaluators.get(file)));
		}
		return HTMLStandards.getStandardSubMenu("viewer", null, links
				.toArray(new Link[0]));
	}
}
