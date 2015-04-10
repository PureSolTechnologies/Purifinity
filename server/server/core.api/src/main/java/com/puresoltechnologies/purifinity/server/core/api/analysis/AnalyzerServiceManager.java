package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Local;

import com.puresoltechnologies.parsers.analyzer.Analyzer;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface AnalyzerServiceManager extends AnalyzerServiceManagerCommon {

    public boolean isActive(String analyzerId);

    public void setActive(String analyzerId, boolean active);

    /**
     * This method creates a {@link Analyzer} instance out of the analyzer id.
     * This analyzer is actually a proxy via remoting to the plugin.
     * 
     * @param evaluatorId
     *            is the id of the evaluator.
     * @return An {@link Evaluator} proxy is returned.
     */
    public ProgrammingLanguageAnalyzer getInstanceById(String analyzerId);

}
