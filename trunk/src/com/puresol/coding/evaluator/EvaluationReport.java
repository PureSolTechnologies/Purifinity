package com.puresol.coding.evaluator;

import java.io.File;

import com.puresol.reporting.ReportingFormat;

public class EvaluationReport {

	public static void report(ProjectEvaluator evaluator, File outputDirectory,
			ReportingFormat format) throws UnsupportedReportingFormatException {
		new EvaluationReport(evaluator, format).createReport(outputDirectory);
	}

	private final ProjectEvaluator evaluator;
	private final ReportingFormat format;

	private EvaluationReport(ProjectEvaluator evaluator, ReportingFormat format) {
		this.evaluator = evaluator;
		this.format = format;
	}

	private void createReport(File outputDirectory)
			throws UnsupportedReportingFormatException {
		ReportGenerator generator = ReportGeneratorFactory.create(evaluator,
				outputDirectory, format);
		generator.createProject(outputDirectory);
	}

}
