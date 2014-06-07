package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;

/**
 * This interface is the central interface for all factories for evaluators. The
 * central attributes of the created evaluators can be checked here before
 * creating them.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorFactory {

	/**
	 * This method returns a name for the evaluator which is created with this
	 * factory.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * This method returns a description for the evaluator which is created with
	 * this factory.
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * This method returns a list with all quality characteristics which are
	 * evaluated with the evaluator created with this factory.
	 * 
	 * @return
	 */
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics();

	/**
	 * <p>
	 * This method returns which parameter will be supported by the evaluator
	 * this factory creates.
	 * </p>
	 * <p>
	 * Important(!): It is not needed that all parameters are always calculated
	 * by the evaluator. It is the sum of all parameter which may be contained
	 * in the results list.
	 * </p>
	 * 
	 * @return
	 */
	public Set<Parameter<?>> getParameters();

	/**
	 * This method actually creates the evaluator.
	 * 
	 * @param analysisRun
	 * @return
	 */
	public Evaluator create();

	/**
	 * This method returns a list of Evaluator classes which are needed to be
	 * evaluated before the evaluator created by this factory can be run.
	 * 
	 * @return
	 */
	public Set<Class<? extends Evaluator>> getDependencies();

	/**
	 * This method returns the implementing class of the evaluators produced
	 * with this factory.
	 * 
	 * @return A {@link Class} is returned which extends {@link Evaluator}.
	 */
	public Class<? extends Evaluator> getEvaluatorClass();
}
