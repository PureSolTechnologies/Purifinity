package com.puresol.purifinity.coding.metrics;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.puresol.commons.utils.FileSearchConfiguration;
import com.puresol.purifinity.coding.analysis.api.AnalysisProject;
import com.puresol.purifinity.coding.analysis.api.AnalysisProjectSettings;
import com.puresol.purifinity.coding.analysis.api.AnalysisStore;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.purifinity.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresol.purifinity.coding.lang.java.Java;

/**
 * This class is used for metrics tests as parent class. This class guarantees a
 * initialized analysis environment.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractMetricTest {

	private static final AnalysisStoreFactory factory = AnalysisStoreFactory
			.getFactory();
	private static final AnalysisStore analysisStore = factory.getInstance();

	@BeforeClass
	public static void checkEnvironment() {
		assertNotNull("Analysis store is null!", analysisStore);

		List<AnalyzableProgrammingLanguage> languages = ProgrammingLanguages
				.createInstance().getAll();
		assertNotNull("The list of languages is null!", languages);
		assertTrue("No programming languages found!", languages.size() > 0);
		assertTrue(languages.contains(Java.getInstance()));
	}

	private AnalysisProject analysisProject;

	private final File directory;
	private final FileSearchConfiguration fileSearchConfiguration;

	public AbstractMetricTest(File directory,
			FileSearchConfiguration fileSearchConfiguration) {
		super();
		assertNotNull("Project directory is null.", directory);
		assertTrue("Project directory '" + directory + "' is not existing.",
				directory.exists());
		this.directory = directory;
		System.out.println("Test test project directory is '" + directory
				+ "'...");
		assertNotNull("Search configuration is null.", fileSearchConfiguration);
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
		assertNotNull("Analysis project was not created and is null.",
				analysisProject);
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
