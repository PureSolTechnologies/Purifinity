package com.puresol.coding.metrics.halstead;

public class HalsteadResult {

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
	private final double vocabularySize;
	/**
	 * Program length
	 */
	private final double programLength;
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

	public HalsteadResult(int differentOperators, int differentOperands,
			int totalOperators, int totalOperands) {
		super();
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

	public double getVocabularySize() {
		return vocabularySize;
	}

	public double getProgramLength() {
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

}
