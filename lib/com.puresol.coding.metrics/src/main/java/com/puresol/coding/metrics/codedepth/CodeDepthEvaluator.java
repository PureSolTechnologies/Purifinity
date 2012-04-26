package com.puresol.coding.metrics.codedepth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.quality.api.QualityCharacteristic;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class CodeDepthEvaluator extends
	AbstractEvaluator<CodeDepthEvaluatorResults> {

    private static final long serialVersionUID = -5093217611195212999L;

    public CodeDepthEvaluator(AnalysisRun analysisRun) {
	super(new EvaluatorInformation(CodeDepthMetric.NAME,
		CodeDepthMetric.DESCRIPTION), analysisRun);
    }

    @Override
    protected Map<String, SourceCodeQuality> processFile(FileAnalysis analysis)
	    throws InterruptedException {
	Map<String, SourceCodeQuality> results = new HashMap<String, SourceCodeQuality>();
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    CodeDepthMetric metric = new CodeDepthMetric(getAnalysisRun(),
		    language, codeRange);
	    metric.schedule();
	    metric.join();
	    results.put(
		    analysis.getAnalyzedFile().getFile().getPath() + ": "
			    + codeRange.getType().getName() + " '"
			    + codeRange.getName() + "'", metric.getQuality());
	}
	return results;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public CodeDepthEvaluatorResults getResults() {
	return null;
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	// TODO Auto-generated method stub

    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub

    }
}
