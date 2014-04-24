package com.puresoltechnologies.purifinity.server.analysisservice.test;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.analysis.impl.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalysisStoreService;

public class AnalysisStoreServiceBeanIT extends
		AbstractAnalysisStoreServiceServerTest {

	@Inject
	private AnalysisStoreService analysisStoreService;

	@Test
	public void test() throws AnalysisStoreException {
		DirectoryRepositoryLocation directoryRepositoryLocation = new DirectoryRepositoryLocation(
				"Test", new File("."));
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(), true);
		AnalysisProjectSettings settings = new AnalysisProjectSettings("name",
				"description", fileSearchConfiguration,
				directoryRepositoryLocation.getSerialization());
		analysisStoreService.createAnalysisProject(settings);
	}
}
