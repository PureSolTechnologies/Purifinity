package com.puresol.coding.metrics.entropy;

import java.util.List;

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class EntropyEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    public EntropyEvaluator(AnalysisRun analysisRun) {
	super(EntropyMetric.NAME, EntropyMetric.DESCRIPTION, analysisRun);
    }

    @Override
    protected void processFile(FileAnalysis analysis)
	    throws InterruptedException {
	EntropyFileResult results = new EntropyFileResult();
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    EntropyMetric metric = new EntropyMetric(getAnalysisRun(),
		    language, codeRange);
	    metric.schedule();
	    metric.join();
	    results.put(
		    analysis.getAnalyzedFile().getFile().getPath() + ": "
			    + codeRange.getType().getName() + " '"
			    + codeRange.getName() + "'", metric.getQuality());
	}
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
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
