package com.puresol.coding.metrics.mccabe;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.uhura.ast.ParserTree;

public class ProjectMcCabeMetric extends AbstractProjectMetric<McCabeMetric> {

	private static final long serialVersionUID = -5093217611195212999L;

	public ProjectMcCabeMetric(ProjectAnalyzer projectAnalyzer) {
		super(projectAnalyzer);
	}

	@Override
	protected Map<String, SourceCodeQuality> processFile(AnalyzedFile file)
			throws IOException {
		Map<String, SourceCodeQuality> results = new HashMap<String, SourceCodeQuality>();
		Analysis analysis = getProjectAnalyzer().getAnalysis(file);
		ProgrammingLanguage language = analysis.getLanguage();
		ParserTree parserTree = analysis.getParserTree();

		for (CodeRange codeRange : language.getAnalyzableCodeRanges(parserTree)) {
			McCabeMetric metric = new McCabeMetric(language, codeRange);
			metric.run();
			results.put(file.toString() + ": " + codeRange.getType().getName()
					+ " '" + codeRange.getName() + "'", metric.getQuality());
		}
		return results;
	}

	@Override
	public String getName() {
		return McCabeMetric.NAME;
	}

	@Override
	public String getDescription() {
		return McCabeMetric.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
