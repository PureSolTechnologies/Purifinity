package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Collection;

import javax.ws.rs.Path;

@Path("rest")
public interface AnalysisServiceRestInterface {

	public Collection<AnalyzerInformation> getAnalyzers();

}
