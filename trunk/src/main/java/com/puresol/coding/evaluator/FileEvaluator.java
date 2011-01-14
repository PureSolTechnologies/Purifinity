package com.puresol.coding.evaluator;

import java.io.IOException;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.utils.Persistence;

public class FileEvaluator {

	public static void evaluate(AnalyzedFile file, Analyzer analyzer) throws IOException {
		List<CodeRangeEvaluatorFactory> factories = CodeRangeEvaluatorManager
				.getAll();
		for (CodeRangeEvaluatorFactory factory : factories) {
			for (CodeRange codeRange : analyzer.getAnalyzableCodeRanges()) {
				CodeRangeEvaluator evaluator = factory.create(
						analyzer.getLanguage(), codeRange);
				evaluator.run();
				try {
					Persistence.persist(evaluator.getReport(),
							file.getReportFile(evaluator, codeRange));
					Persistence.persist(evaluator.getResults(),
							file.getResultsFile(evaluator, codeRange));
					// TODO think about improvement
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
