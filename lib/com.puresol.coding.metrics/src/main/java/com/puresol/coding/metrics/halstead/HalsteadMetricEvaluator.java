package com.puresol.coding.metrics.halstead;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.quality.api.QualityCharacteristic;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class HalsteadMetricEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    public HalsteadMetricEvaluator(AnalysisRun analysisRun) {
	super(new EvaluatorInformation(HalsteadMetric.NAME,
		HalsteadMetric.DESCRIPTION), analysisRun);
    }

    @Override
    protected Map<String, SourceCodeQuality> processFile(FileAnalysis analysis)
	    throws IOException, FileStoreException {
	Map<String, SourceCodeQuality> results = new HashMap<String, SourceCodeQuality>();
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    HalsteadMetric metric = new HalsteadMetric(getAnalysisRun(),
		    language, codeRange);
	    metric.schedule();
	    results.put(
		    analysis.getAnalyzedFile().getFile().getPath() + ": "
			    + codeRange.getType().getName() + " '"
			    + codeRange.getName() + "'", metric.getQuality());
	}
	return results;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }
}
