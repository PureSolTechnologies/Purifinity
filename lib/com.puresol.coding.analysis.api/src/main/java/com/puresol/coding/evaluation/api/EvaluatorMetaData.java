package com.puresol.coding.evaluation.api;

/**
 * This class provides additional information about an evaluator. This class is
 * provided as immutable to have all evaluators only one instance of this class
 * to return for all clients.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorMetaData {

    /**
     * Contains the name of the evaluator.
     */
    private final String name;
    private final String description;

    public EvaluatorMetaData(String name, String description) {
	super();
	this.name = name;
	this.description = description;
    }

    /**
     * Returns the name of the evaluator.
     * 
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     * Returns the description of the evaluator.
     * 
     * @return
     */
    public String getDescription() {
	return description;
    }

}
