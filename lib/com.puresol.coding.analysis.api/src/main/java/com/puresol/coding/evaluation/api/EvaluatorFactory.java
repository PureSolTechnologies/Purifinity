package com.puresol.coding.evaluation.api;

import java.util.List;

import com.puresol.coding.quality.QualityCharacteristic;

/**
 * This interface is the central interface for all factories for evaluators. The
 * central attributes of the created evaluators can be checked here before
 * creating them.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorFactory {

	public String getName();

	public String getDescription();

	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

}
