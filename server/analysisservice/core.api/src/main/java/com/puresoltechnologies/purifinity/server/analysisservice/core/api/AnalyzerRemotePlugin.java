package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;

@Remote
public interface AnalyzerRemotePlugin extends ProgrammingLanguageAnalyzer {

}
