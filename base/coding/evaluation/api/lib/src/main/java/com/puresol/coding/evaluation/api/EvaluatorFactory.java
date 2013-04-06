package com.puresol.coding.evaluation.api;

import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.utils.math.Parameter;

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
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

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
	public List<Parameter<?>> getParameters();

	/**
	 * This method actually creates the evaluator.
	 * 
	 * @param analysisRun
	 * @return
	 */
	public Evaluator create(AnalysisRun analysisRun);

	/**
	 * This method actually creates the evaluator. The difference is here, that
	 * this method creates an {@link Evaluator} for a subtree specified by a
	 * {@link HashIdFileTree}.
	 * 
	 * @param analysisRun
	 *            is the analysis run to be run on.
	 * @param path
	 *            is the project internal path to specified the subtree to work
	 *            on.
	 * @return An {@link Evaluator} is returned which runs the evaluation on the
	 *         specified subtree.
	 */
	public Evaluator create(AnalysisRun analysisRun, HashIdFileTree path);

	/**
	 * This method returns a list of Evaluator classes which are needed to be
	 * evaluated before the evaluator created by this factory can be run.
	 * 
	 * @return
	 */
	public List<Class<? extends Evaluator>> getDependencies();

	/**
	 * This method returns the implementing class of the evaluators produced
	 * with this factory.
	 * 
	 * @return A {@link Class} is returned which extends {@link Evaluator}.
	 */
	public Class<? extends Evaluator> getEvaluatorClass();
}
