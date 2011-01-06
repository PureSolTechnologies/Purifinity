package com.puresol.coding.evaluator;

public class EvaluatorOutput {

	private final String sectionName;
	private final String text;

	public EvaluatorOutput(String sectionName, String text) {
		super();
		this.sectionName = sectionName;
		this.text = text;
	}

	public String getSectionName() {
		return sectionName;
	}

	public String getText() {
		return text;
	}

}
