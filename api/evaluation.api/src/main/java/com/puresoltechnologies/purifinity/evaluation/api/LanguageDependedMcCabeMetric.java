package com.puresoltechnologies.purifinity.evaluation.api;

import java.io.Serializable;

import com.puresoltechnologies.parsers.ust.AbstractProduction;

/**
 * This interface is used to implement a part of the McCabe metric (cyclomatic
 * complexity) implementation within language packages. This implementation is
 * used to distinguish between node which increase the cyclomatic complexity and
 * those who don't.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface LanguageDependedMcCabeMetric extends Serializable {

    /**
     * This method is implemented in language packs for determining whether the
     * cyclomatic complexity is to be increased or not.
     * 
     * @param production
     *            is a {@link AbstractProduction} of the language to be checked.
     * @return An integer is returned about how much the complexity is to be
     *         increased. 0 is to be returned in case no complexity is to be
     *         changed.
     */
    public int increasesCyclomaticComplexityBy(AbstractProduction production);

}
