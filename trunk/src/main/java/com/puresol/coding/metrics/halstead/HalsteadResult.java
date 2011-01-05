package com.puresol.coding.metrics.halstead;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.evaluator.Result;

public class HalsteadResult {

	private static final Translator translator = Translator
			.getTranslator(HalsteadResult.class);

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
	private final List<Result> results = new ArrayList<Result>();

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
		createResultsList();
	}

	private void createResultsList() {
		results.add(new Result("N1", translator
				.i18n("Total number of operators"), totalOperators, ""));

		results.add(new Result("N2", translator
				.i18n("Total number of operants"), totalOperands, ""));

		results.add(new Result("n1", translator
				.i18n("Number of unique operators"), differentOperators, ""));

		results.add(new Result("n2", translator
				.i18n("Number of unique operants"), differentOperands, ""));

		results.add(new Result("N", translator.i18n("Program length"),
				programLength, ""));

		results.add(new Result("n", translator.i18n("Vocabulary Size"),
				vocabularySize, ""));

		results.add(new Result("V", translator.i18n("Halstead volume"),
				halsteadVolume, ""));

		results.add(new Result("Hl", translator.i18n("Halstead length"),
				halsteadLength, ""));

		results.add(new Result("D", translator.i18n("Difficulty"), difficulty,
				""));

		results.add(new Result("L", translator.i18n("Program level"),
				programLevel, ""));

		results.add(new Result("E", translator.i18n("Implementation effort"),
				implementationEffort, ""));

		results.add(new Result("T", translator.i18n("Implementation time"),
				implementationTime, translator.i18n("Seconds")));

		results.add(new Result("B",
				translator.i18n("Number of delivered bugs"), estimatedBugs, ""));
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

	public List<Result> getResults() {
		return results;
	}

}
