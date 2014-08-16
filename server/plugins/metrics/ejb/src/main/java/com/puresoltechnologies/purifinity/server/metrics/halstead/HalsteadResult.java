package com.puresoltechnologies.purifinity.server.metrics.halstead;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class HalsteadResult implements Serializable {

	private static final long serialVersionUID = -2269802940509163015L;

	/**
	 * number of different operators
	 */
	private final int differentOperators;
	/**
	 * number of different operands
	 */
	private final int differentOperands;
	/**
	 * total number of operators
	 */
	private final int totalOperators;
	/**
	 * total number of operands
	 */
	private final int totalOperands;
	/**
	 * Vocabulary size
	 */
	private final int vocabularySize;
	/**
	 * Program length
	 */
	private final int programLength;
	/**
	 * Halstead Length
	 */
	private final double halsteadLength;
	/**
	 * Halstead Volume
	 */
	private final double halsteadVolume;
	/**
	 * Difficulty
	 */
	private final double difficulty;
	/**
	 * Program Level
	 */
	private final double programLevel;
	/**
	 * Implementation Effort.
	 */
	private final double implementationEffort;
	/**
	 * Implementation Time [s]
	 */
	private final double implementationTime;
	/**
	 * Estimated Bugs
	 */
	private final double estimatedBugs;
	private final Map<String, Integer> operands;
	private final Map<String, Integer> operators;

	private final List<MetricValue<?>> results = new ArrayList<>();

	public HalsteadResult(Map<String, Integer> operands,
			Map<String, Integer> operators) {
		super();
		this.operands = operands;
		this.operators = operators;
		this.differentOperators = operators.keySet().size();
		this.differentOperands = operands.keySet().size();
		int totalOperators = 0;
		for (Integer i : operators.values()) {
			totalOperators += i;
		}
		this.totalOperators = totalOperators;
		int totalOperands = 0;
		for (Integer i : operands.values()) {
			totalOperands += i;
		}
		this.totalOperands = totalOperands;
		vocabularySize = differentOperators + differentOperands;
		programLength = totalOperands + totalOperands;
		halsteadLength = differentOperators * Math.log(differentOperators)
				/ Math.log(2) + differentOperands * Math.log(differentOperands)
				/ Math.log(2);
		halsteadVolume = programLength * Math.log(vocabularySize) / Math.log(2);
		difficulty = differentOperators / 2.0 * totalOperands
				/ differentOperands;
		programLevel = 1 / difficulty;
		implementationEffort = halsteadVolume * difficulty;
		implementationTime = implementationEffort / 18.0;
		estimatedBugs = Math.exp(2.0 / 3.0 * Math.log(implementationEffort)) / 3000.0;
		createResultsList();
	}

	private void createResultsList() {
		results.add(new MetricValue<Integer>(totalOperators,
				HalsteadMetricEvaluatorParameter.TOTAL_OPERATORS));
		results.add(new MetricValue<Integer>(totalOperands,
				HalsteadMetricEvaluatorParameter.TOTAL_OPERANDS));
		results.add(new MetricValue<Integer>(differentOperators,
				HalsteadMetricEvaluatorParameter.DIFFERENT_OPERATORS));
		results.add(new MetricValue<Integer>(differentOperands,
				HalsteadMetricEvaluatorParameter.DIFFERENT_OPERANDS));
		results.add(new MetricValue<Integer>(programLength,
				HalsteadMetricEvaluatorParameter.PROGRAM_LENGTH));
		results.add(new MetricValue<Integer>(vocabularySize,
				HalsteadMetricEvaluatorParameter.VOCABULARY_SIZE));
		results.add(new MetricValue<Double>(halsteadVolume,
				HalsteadMetricEvaluatorParameter.HALSTEAD_VOLUMNE));
		results.add(new MetricValue<Double>(halsteadLength,
				HalsteadMetricEvaluatorParameter.HALSTEAD_LENGTH));
		results.add(new MetricValue<Double>(difficulty,
				HalsteadMetricEvaluatorParameter.DIFFICULTY));
		results.add(new MetricValue<Double>(programLevel,
				HalsteadMetricEvaluatorParameter.PROGRAM_LEVEL));
		results.add(new MetricValue<Double>(implementationEffort,
				HalsteadMetricEvaluatorParameter.IMPLEMENTATION_EFFORT));
		results.add(new MetricValue<Double>(implementationTime,
				HalsteadMetricEvaluatorParameter.IMPLEMENTATION_TIME));
		results.add(new MetricValue<Double>(estimatedBugs,
				HalsteadMetricEvaluatorParameter.ESTIMATED_BUGS));
	}

	public Map<String, Integer> getOperands() {
		return operands;
	}

	public Map<String, Integer> getOperators() {
		return operators;
	}

	public int getDifferentOperators() {
		return differentOperators;
	}

	public int getDifferentOperands() {
		return differentOperands;
	}

	public int getTotalOperators() {
		return totalOperators;
	}

	public int getTotalOperands() {
		return totalOperands;
	}

	public int getVocabularySize() {
		return vocabularySize;
	}

	public int getProgramLength() {
		return programLength;
	}

	public double getHalsteadLength() {
		return halsteadLength;
	}

	public double getHalsteadVolume() {
		return halsteadVolume;
	}

	public double getDifficulty() {
		return difficulty;
	}

	public double getProgramLevel() {
		return programLevel;
	}

	public double getImplementationEffort() {
		return implementationEffort;
	}

	public double getImplementationTime() {
		return implementationTime;
	}

	public double getEstimatedBugs() {
		return estimatedBugs;
	}

	public List<MetricValue<?>> getResults() {
		return results;
	}

	public List<MetricValue<?>> getResultsMap() {
		return results;
	}
}
