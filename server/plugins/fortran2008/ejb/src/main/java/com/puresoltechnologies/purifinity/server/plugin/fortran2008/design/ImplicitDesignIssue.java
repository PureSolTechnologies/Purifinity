package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import java.util.Date;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.design.AbstractDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.FileDesignIssues;

public class ImplicitDesignIssue extends AbstractDesignIssues implements FileDesignIssues {

    private static final long serialVersionUID = -7797786693865227186L;

    private HashId hashId;
    private SourceCodeLocation sourceCodeLocation;

    public ImplicitDesignIssue(HashId hashId, SourceCodeLocation sourceCodeLocation, Date time) {
	super(FortranDesignEvaluator.NAME, FortranDesignEvaluator.PLUGIN_VERSION, time);
	this.hashId = hashId;
	this.sourceCodeLocation = sourceCodeLocation;
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

}
