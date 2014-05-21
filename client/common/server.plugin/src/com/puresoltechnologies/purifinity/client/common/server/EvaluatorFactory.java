package com.puresoltechnologies.purifinity.client.common.server;

import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;

public interface EvaluatorFactory {

	String getName();

	String getDescription();

	Set<Parameter<?>> getParameters();

	Class<? extends Evaluator> getEvaluatorClass();

	Set<QualityCharacteristic> getEvaluatedQualityCharacteristics();

	Evaluator create(AnalysisRun analysisRun, AnalysisFileTree path);

	Evaluator create(AnalysisRun analysisRun);

}
