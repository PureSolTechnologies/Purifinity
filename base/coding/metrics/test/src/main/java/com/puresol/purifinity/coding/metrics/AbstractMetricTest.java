package com.puresol.purifinity.coding.metrics;

import java.io.File;

import org.junit.After;
import org.junit.Before;

import com.puresol.commons.utils.FileSearchConfiguration;
import com.puresol.purifinity.coding.analysis.api.AnalysisProject;
import com.puresol.purifinity.coding.analysis.api.AnalysisProjectSettings;
import com.puresol.purifinity.coding.analysis.api.AnalysisStore;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.purifinity.coding.analysis.api.DirectoryRepositoryLocation;

public abstract class AbstractMetricTest {

	private static final AnalysisStoreFactory factory = AnalysisStoreFactory
			.getFactory();
	private static final AnalysisStore analysisStore = factory.getInstance();

	private AnalysisProject analysisProject;

	private final File directory;
	private final FileSearchConfiguration fileSearchConfiguration;

	public AbstractMetricTest(File directory,
			FileSearchConfiguration fileSearchConfiguration) {
		super();
		this.directory = directory;
		this.fileSearchConfiguration = fileSearchConfiguration;
	}

	@Before
	public final void setup() throws AnalysisStoreException {
		analysisProject = analysisStore
				.createAnalysis(new AnalysisProjectSettings("TestProject",
						"This project was created for testing purposes.",
						fileSearchConfiguration,
						new DirectoryRepositoryLocation("TestProject",
								directory)));
	}

	@After
	public final void destroy() throws AnalysisStoreException {
		analysisStore
				.removeAnalysis(analysisProject.getInformation().getUUID());
	}

	protected AnalysisProject getAnalysisProject() {
		return analysisProject;
	}

}
