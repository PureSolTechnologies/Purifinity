package com.puresol.coding.metrics.maintainability;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.EvaluatorOutput;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.uhura.ast.ParserTree;

public class ProjectMaintainabilityIndex extends
		AbstractProjectMetric<MaintainabilityIndex> {

	private static final long serialVersionUID = -5093217611195212999L;

	public ProjectMaintainabilityIndex(ProjectAnalyzer projectAnalyzer) {
		super(projectAnalyzer);
	}

	@Override
	protected Map<String, SourceCodeQuality> processFile(File file) {

		Map<String, SourceCodeQuality> results = new HashMap<String, SourceCodeQuality>();

		Analyzer analyzer = getProjectAnalyzer().getAnalyzer(file);
		ProgrammingLanguage language = analyzer.getLanguage();
		ParserTree parserTree = analyzer.getParserTree();

		for (CodeRange codeRange : language.getAnalyzableCodeRanges(parserTree)) {
			MaintainabilityIndex metric = new MaintainabilityIndex(language,
					codeRange);
			metric.run();
			results.put(file.toString() + ": " + codeRange.getType().getName()
					+ " '" + codeRange.getName() + "'", metric.getQuality());
		}
		return results;
	}

	@Override
	public String getName() {
		return MaintainabilityIndex.NAME;
	}

	@Override
	public String getDescription() {
		return MaintainabilityIndex.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return MaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<EvaluatorOutput> getTextOutput() {
		return null;
	}
}
