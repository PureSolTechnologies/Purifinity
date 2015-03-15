package com.puresoltechnologies.purifinity.evaluation.api;

import com.puresoltechnologies.versioning.Version;

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
     * Contains the version of the evaluator.
     */
    private final Version version;
    /**
     * Contains the type of the evaluator.
     */
    private final EvaluatorType evaluatorType;
    /**
     * Contains the description of the evaluator.
     */
    private final String description;

    public EvaluatorInformation(String id, String name, Version version,
	    EvaluatorType evaluatorType, String description) {
	super();
	this.id = id;
	this.name = name;
	this.version = version;
	this.evaluatorType = evaluatorType;
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
     * Returns the version of the evaluator.
     * 
     * @return
     */
    public Version getVersion() {
	return version;
    }

    /**
     * Returns the type classification of the evaluator.
     * 
     * @return A {@link EvaluatorType} constant is returned.
     */
    public EvaluatorType getEvaluatorType() {
	return evaluatorType;
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
