package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import java.util.List;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractCodeRangeEvaluator;

public class FortranDesignCodeRangeEvaluator extends AbstractCodeRangeEvaluator {

    public FortranDesignCodeRangeEvaluator(String name, AnalysisRun analysisRun, CodeRange codeRange) {
	super(name, analysisRun, codeRange);
    }

    @Override
    public String getDescription() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<MetricValue<?>> getResults() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Severity getQuality() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean run() {
	// TODO Auto-generated method stub
	return false;
    }

}
