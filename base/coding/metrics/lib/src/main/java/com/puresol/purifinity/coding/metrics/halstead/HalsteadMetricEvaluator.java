package com.puresol.purifinity.coding.metrics.halstead;

import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.CodeAnalysis;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.QualityCharacteristic;
import com.puresol.purifinity.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;
import com.puresol.purifinity.uhura.ust.eval.EvaluationException;

public class HalsteadMetricEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private final EvaluatorStore store;

    public HalsteadMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
	super(HalsteadMetric.NAME, HalsteadMetric.DESCRIPTION, analysisRun,
		path);
	store = createEvaluatorStore();
    }

    @Override
    protected void processFile(CodeAnalysis analysis)
	    throws InterruptedException, EvaluationException {
	HalsteadMetricFileResults results = new HalsteadMetricFileResults();
	ProgrammingLanguages programmingLanguages = ProgrammingLanguages
		.createInstance();
	try {
	    ProgrammingLanguage language = programmingLanguages.findByName(
		    analysis.getLanguageName(), analysis.getLanguageVersion());

	    for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
		HalsteadMetric metric = new HalsteadMetric(getAnalysisRun(),
			language, codeRange);
		execute(metric);
		results.add(new HalsteadMetricResult(analysis.getAnalyzedFile()
			.getSourceLocation(), codeRange.getType(), codeRange
			.getCanonicalName(), metric.getHalsteadResults(),
			metric.getQuality()));
	    }
	} finally {
	    IOUtils.closeQuietly(programmingLanguages);
	}
	store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	// intentionally left blank
    }

    @Override
    protected void processProject() throws InterruptedException {
	// intentionally left blank
    }
}
