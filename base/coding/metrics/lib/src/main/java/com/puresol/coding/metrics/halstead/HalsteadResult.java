package com.puresol.coding.metrics.halstead;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.puresol.coding.evaluation.impl.Result;

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
	private final Map<String, Integer> opertors;

	private final List<Result> results = new ArrayList<Result>();

	public HalsteadResult(Map<String, Integer> operands,
			Map<String, Integer> opertors, int differentOperators,
			int differentOperands, int totalOperators, int totalOperands) {
		super();
		this.operands = operands;
		this.opertors = opertors;
		this.differentOperators = differentOperators;
		this.differentOperands = differentOperands;
		this.totalOperators = totalOperators;
		this.totalOperands = totalOperands;
		vocabularySize = differentOperators + differentOperands;
		programLength = totalOperators + totalOperands;
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
		results.add(new Result("N1", "Total number of operators",
				totalOperators, ""));

		results.add(new Result("N2", "Total number of operants", totalOperands,
				""));

		results.add(new Result("n1", "Number of unique operators",
				differentOperators, ""));

		results.add(new Result("n2", "Number of unique operants",
				differentOperands, ""));

		results.add(new Result("N", "Program length", programLength, ""));

		results.add(new Result("n", "Vocabulary Size", vocabularySize, ""));

		results.add(new Result("V", "Halstead volume", halsteadVolume, ""));

		results.add(new Result("Hl", "Halstead length", halsteadLength, ""));

		results.add(new Result("D", "Difficulty", difficulty, ""));

		results.add(new Result("L", "Program level", programLevel, ""));

		results.add(new Result("E", "Implementation effort",
				implementationEffort, ""));

		results.add(new Result("T", "Implementation time", implementationTime,
				"Seconds"));

		results.add(new Result("B", "Number of delivered bugs", estimatedBugs,
				""));
	}

	public Map<String, Integer> getOperands() {
		return operands;
	}

	public Map<String, Integer> getOperators() {
		return opertors;
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

	public List<Result> getResults() {
		return results;
	}

}
