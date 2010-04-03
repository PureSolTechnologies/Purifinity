package com.puresol.coding.evaluator;

import java.io.File;

import com.puresol.coding.reporting.StandardReport;

public class HTMLReportGenerator extends AbstractReportGenerator {

	private final StandardReport report;

	public HTMLReportGenerator(CodeEvaluator codeEvaluator, File outputDirectory) {
		super(codeEvaluator);
		report = new StandardReport(outputDirectory);
	}

	@Override
	public void createProject(File outputDirectory) {
		createStandardFiles();
		createProjectReport();
		createEvaluatorReports();
		createFileReports();
	}

	private void createStandardFiles() {
		report.createStandardFiles();
	}

	private void createProjectReport() {
		// TODO
	}

	private void createEvaluatorReports() {
		// TODO
	}

	private void createFileReports() {
		// TODO
	}

}
