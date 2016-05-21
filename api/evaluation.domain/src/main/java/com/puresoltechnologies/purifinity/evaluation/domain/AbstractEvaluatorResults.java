package com.puresoltechnologies.purifinity.evaluation.domain;

import java.util.Date;

import com.puresoltechnologies.versioning.Version;

public abstract class AbstractEvaluatorResults implements EvaluationResults {

    private static final long serialVersionUID = 2746877960935350933L;

    private final String evaluatorId;
    private final Version evaluatorVersion;
    private final Date time;

    public AbstractEvaluatorResults(String evaluatorId, Version evaluatorVersion, Date time) {
	super();
	this.evaluatorId = evaluatorId;
	this.evaluatorVersion = evaluatorVersion;
	this.time = time;
    }

    @Override
    public final String getEvaluatorId() {
	return evaluatorId;
    }

    @Override
    public final Version getEvaluatorVersion() {
	return evaluatorVersion;
    }

    @Override
    public final Date getTime() {
	return time;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((evaluatorId == null) ? 0 : evaluatorId.hashCode());
	result = prime * result + ((evaluatorVersion == null) ? 0 : evaluatorVersion.hashCode());
	result = prime * result + ((time == null) ? 0 : time.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractEvaluatorResults other = (AbstractEvaluatorResults) obj;
	if (evaluatorId == null) {
	    if (other.evaluatorId != null)
		return false;
	} else if (!evaluatorId.equals(other.evaluatorId))
	    return false;
	if (evaluatorVersion == null) {
	    if (other.evaluatorVersion != null)
		return false;
	} else if (!evaluatorVersion.equals(other.evaluatorVersion))
	    return false;
	if (time == null) {
	    if (other.time != null)
		return false;
	} else if (!time.equals(other.time))
	    return false;
	return true;
    }

}
