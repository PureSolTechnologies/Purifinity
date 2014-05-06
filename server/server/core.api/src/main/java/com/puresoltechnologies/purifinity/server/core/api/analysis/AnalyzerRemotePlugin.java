package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;

@Remote
public interface AnalyzerRemotePlugin extends ProgrammingLanguageAnalyzer {

}
