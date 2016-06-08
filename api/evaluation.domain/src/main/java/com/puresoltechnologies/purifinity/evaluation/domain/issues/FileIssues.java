package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public interface FileIssues extends Issues {

    /**
     * This method returns the {@link HashId} of the file for which the values
     * are for.
     * 
     * @return A {@link HashId} is returned containing the hash id.
     */
    public HashId getHashId();

    /**
     * This method returns the source code location of the source for which the
     * metrics are for.
     * 
     * @return A {@link SourceCodeLocation} is returned containing the
     *         information about the location of the source.
     */
    public SourceCodeLocation getSourceCodeLocation();

    public List<CodeRangeIssues> getCodeRangeIssues();

}
