package com.puresol.coding.metrics.sloc;

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

public class SLOCEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    public SLOCEvaluator(AnalysisRun analysisRun) {
	super(
		new EvaluatorInformation(SLOCMetric.NAME,
			SLOCMetric.DESCRIPTION), analysisRun);
    }

    @Override
    protected Map<String, SourceCodeQuality> processFile(FileAnalysis analysis)
	    throws InterruptedException {
	Map<String, SourceCodeQuality> results = new HashMap<String, SourceCodeQuality>();
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    SLOCMetric metric = new SLOCMetric(getAnalysisRun(), language,
		    codeRange);
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
	return SLOCMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public SLOCEvaluatorResult getResults() {
	// TODO Auto-generated method stub
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
