package com.puresoltechnologies.purifinity.evaluation.api;

/**
 * This class provides additional information about an evaluator. This class is
 * provided as immutable to have all evaluators only one instance of this class
 * to return for all clients.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorInformation {

    private final String id;
    /**
     * Contains the name of the evaluator.
     */
    private final String name;
    /**
     * Contains the description of the evaluator.
     */
    private final String description;

    public EvaluatorInformation(String id, String name, String description) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
    }

    public String getId() {
	return id;
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
