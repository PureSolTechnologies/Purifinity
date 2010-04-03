package com.puresol.coding.evaluator;

import java.io.File;

import com.puresol.reporting.ReportingFormat;

public class EvaluationReport {

	public static void report(CodeEvaluator evaluator, File outputDirectory,
			ReportingFormat format) throws UnsupportedReportingFormatException {
		new EvaluationReport(evaluator, format).createReport(outputDirectory);
	}

	private final CodeEvaluator evaluator;
	private final ReportingFormat format;

	private EvaluationReport(CodeEvaluator evaluator, ReportingFormat format) {
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
