package com.puresoltechnologies.purifinity.framework.evaluation.metrics;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.analysis.impl.DirectoryRepositoryLocation;
import com.puresoltechnologies.purifinity.framework.analysis.impl.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.framework.lang.java7.Java;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;

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

		List<ProgrammingLanguageAnalyzer> languages = ProgrammingLanguages
				.createInstance().getAll();
		assertNotNull("The list of languages is null!", languages);
		assertTrue("No programming languages found!", languages.size() > 0);
		assertTrue(languages.contains(Java.getInstance()));
	}

	@BeforeClass
	public static void cleanCodeAnalysisDirectory() {
		File codeAnalysisDirectory = null;
		assertNotNull("Storage directory is not available.",
				codeAnalysisDirectory);
		// if (codeAnalysisDirectory.exists()) {
		// DirectoryUtilities.deleteDirectoryRecursivly(codeAnalysisDirectory);
		// }
	}

	private AnalysisProjectInformation analysisProjectInformation;

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
		analysisProjectInformation = analysisStore
				.createAnalysisProject(new AnalysisProjectSettings(
						"TestProject",
						"This project was created for testing purposes.",
						fileSearchConfiguration,
						new DirectoryRepositoryLocation("TestProject",
								directory).getSerialization()));
		assertNotNull("Analysis project was not created and is null.",
				analysisProjectInformation);
	}

	@After
	public final void destroy() throws AnalysisStoreException {
		analysisStore.removeAnalysisProject(analysisProjectInformation
				.getUUID());
	}

	protected AnalysisProjectInformation getAnalysisProject() {
		return analysisProjectInformation;
	}

}
