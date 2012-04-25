package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class CoCoMoServiceFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return CoCoMo.DESCRIPTION;
    }

    @Override
    public String getName() {
	return CoCoMo.NAME;
    }

    /**
     * {@inheritDoc}
     * 
     * For CoCoMo there is no quality characteristics assigned. It's a pure
     * economic evaluation.
     */
    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return new ArrayList<QualityCharacteristic>();
    }

    @Override
    public CoCoMo create(AnalysisRun analysisRun) {
	return new CoCoMo(analysisRun);
    }

}
