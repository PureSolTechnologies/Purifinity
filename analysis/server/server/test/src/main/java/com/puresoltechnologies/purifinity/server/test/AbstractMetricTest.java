package com.puresoltechnologies.purifinity.server.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.ProjectManager;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;

/**
 * This class is used for metrics tests as parent class. This class guarantees a
 * initialized analysis environment.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractMetricTest extends AbstractServerTest {

    @Inject
    private ProjectManager analysisStore;

    @Inject
    private AnalyzerServiceManager analyzerServiceManager;

    @BeforeClass
    public static void cleanCodeAnalysisDirectory() {
	File codeAnalysisDirectory = null;
	assertNotNull("Storage directory is not available.", codeAnalysisDirectory);
	// if (codeAnalysisDirectory.exists()) {
	// DirectoryUtilities.deleteDirectoryRecursivly(codeAnalysisDirectory);
	// }
    }

    @Before
    public void checkEnvironment() {
	assertNotNull("Analysis store is null!", analysisStore);

	Collection<AnalyzerServiceInformation> languages = analyzerServiceManager.getServices();
	assertNotNull("The list of languages is null!", languages);
	assertTrue("No programming languages found!", languages.size() > 0);
    }

    private AnalysisProjectInformation analysisProjectInformation;

    private final File directory;
    private final FileSearchConfiguration fileSearchConfiguration;

    public AbstractMetricTest(File directory, FileSearchConfiguration fileSearchConfiguration) {
	super();
	assertNotNull("Project directory is null.", directory);
	assertTrue("Project directory '" + directory + "' is not existing.", directory.exists());
	this.directory = directory;
	System.out.println("Test test project directory is '" + directory + "'...");
	assertNotNull("Search configuration is null.", fileSearchConfiguration);
	this.fileSearchConfiguration = fileSearchConfiguration;
    }

    @Before
    public final void setup() throws AnalysisStoreException {
	Properties properties = new Properties();
	properties.setProperty("directory", directory.getPath());
	analysisProjectInformation = analysisStore.createAnalysisProject("test_project",
		new AnalysisProjectSettings("TestProject", "This project was created for testing purposes.", null,
			fileSearchConfiguration, properties));
	assertNotNull("Analysis project was not created and is null.", analysisProjectInformation);
    }

    @After
    public final void destroy() throws AnalysisStoreException {
	analysisStore.removeAnalysisProject(analysisProjectInformation.getProjectId());
    }

    protected AnalysisProjectInformation getAnalysisProject() {
	return analysisProjectInformation;
    }

}
