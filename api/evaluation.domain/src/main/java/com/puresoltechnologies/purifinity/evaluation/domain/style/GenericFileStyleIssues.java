package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.versioning.Version;

public class GenericFileStyleIssues extends AbstractStyleResults {

    private static final long serialVersionUID = -2265405120665928206L;

    private final Set<StyleParameter> parameters = new LinkedHashSet<>();
    private final List<GenericCodeRangeStyleIssues> codeRangeMetrics = new ArrayList<>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public GenericFileStyleIssues(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time, Set<StyleParameter> parameters) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.sourceCodeLocation = sourceCodeLocation;
	this.parameters.addAll(parameters);
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    @Override
    public List<GenericCodeRangeStyleIssues> getCodeRangeStyleResults() {
	// TODO Auto-generated method stub
	return null;
    }

}
