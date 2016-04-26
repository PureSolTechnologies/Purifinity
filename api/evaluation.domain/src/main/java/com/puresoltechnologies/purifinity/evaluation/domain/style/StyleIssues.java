package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.EvaluationResults;

public interface StyleIssues extends EvaluationResults {

    /**
     * This method returns the {@link HashId} of the file for which the style
     * results are for.
     * 
     * @return A {@link HashId} is returned containing the hash id.
     */
    public HashId getHashId();

    /**
     * This method returns the source code location of the source for which the
     * style results are for.
     * 
     * @return A {@link SourceCodeLocation} is returned containing the
     *         information about the location of the source.
     */
    public SourceCodeLocation getSourceCodeLocation();

    public List<GenericCodeRangeStyleIssues> getCodeRangeStyleResults();

}
