package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class CoCoMoServiceFactory implements ProjectEvaluatorFactory {

    @Override
    public ProjectEvaluator create(AnalysisRunImpl projectAnalyser) {
	CoCoMo cocomo = new CoCoMo(projectAnalyser);
	return cocomo;
    }

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

}
