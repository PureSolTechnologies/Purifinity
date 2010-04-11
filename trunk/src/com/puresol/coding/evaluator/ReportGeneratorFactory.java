package com.puresol.coding.evaluator;

import java.io.File;

import com.puresol.reporting.ReportingFormat;

public class ReportGeneratorFactory {

	public static ReportGenerator create(ProjectEvaluator evaluator,
			File outputDirectory, ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.HTML) {
			return new HTMLReportGenerator(evaluator, outputDirectory);
		}
		throw new UnsupportedReportingFormatException(format);
	}

}
