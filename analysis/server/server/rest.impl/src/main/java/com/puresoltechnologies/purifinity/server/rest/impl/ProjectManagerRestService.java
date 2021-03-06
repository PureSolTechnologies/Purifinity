package com.puresoltechnologies.purifinity.server.rest.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.ProjectManager;
import com.puresoltechnologies.purifinity.server.rest.api.ProjectManagerRestInterface;

public class ProjectManagerRestService implements ProjectManagerRestInterface {

    @Inject
    private ProjectManager analysisStore;

    @Override
    public AnalysisProjectInformation createAnalysisProject(String identifier, AnalysisProjectSettings settings)
	    throws AnalysisStoreException {
	return analysisStore.createAnalysisProject(identifier, settings);
    }

    @Override
    public List<AnalysisProjectInformation> readAllAnalysisProjectInformation() throws AnalysisStoreException {
	return analysisStore.readAllAnalysisProjectInformation();
    }

    @Override
    public List<AnalysisProject> readAllAnalysisProjects() throws AnalysisStoreException {
	return analysisStore.readAllAnalysisProjects();
    }

    @Override
    public AnalysisProjectInformation readAnalysisProjectInformation(String projectId) throws AnalysisStoreException {
	return analysisStore.readAnalysisProjectInformation(projectId);
    }

    @Override
    public void removeAnalysisProject(String projectId) throws AnalysisStoreException {
	analysisStore.removeAnalysisProject(projectId);
    }

    @Override
    public AnalysisProjectSettings readAnalysisProjectSettings(String projectId) throws AnalysisStoreException {
	return analysisStore.readAnalysisProjectSettings(projectId);
    }

    @Override
    public void updateAnalysisProjectSettings(String projectId, AnalysisProjectSettings settings)
	    throws AnalysisStoreException {
	analysisStore.updateAnalysisProjectSettings(projectId, settings);
    }

    @Override
    public List<AnalysisRunInformation> readAllRunInformation(String projectId) throws AnalysisStoreException {
	return analysisStore.readAllRunInformation(projectId);
    }

    @Override
    public AnalysisRun readAnalysisRun(String projectId, long runId) throws AnalysisStoreException {
	AnalysisRunInformation information = analysisStore.readAnalysisRunInformation(projectId, runId);
	return analysisStore.readAnalysisRun(information);
    }

    @Override
    public AnalysisRunInformation readAnalysisRunInformation(String projectId, long runId)
	    throws AnalysisStoreException {
	return analysisStore.readAnalysisRunInformation(projectId, runId);
    }

    @Override
    public AnalysisRunInformation readLastAnalysisRun(String projectId) throws AnalysisStoreException {
	return analysisStore.readLastAnalysisRun(projectId);
    }

    @Override
    public void removeAnalysisRun(String projectId, long runId) throws AnalysisStoreException {
	analysisStore.removeAnalysisRun(projectId, runId);
    }

    @Override
    public FileSearchConfiguration readSearchConfiguration(String projectId, long runId) throws AnalysisStoreException {
	return analysisStore.readSearchConfiguration(projectId, runId);
    }

    @Override
    public AnalysisFileTree readAnalysisFileTree(String projectId, long runId) throws AnalysisStoreException {
	AnalysisFileTree readAnalysisFileTree = analysisStore.readAnalysisFileTree(projectId, runId);
	return readAnalysisFileTree;
    }

    @Override
    public AnalysisProject readAnalysisProject(String projectId) throws AnalysisStoreException {
	return analysisStore.readAnalysisProject(projectId);
    }

    @Override
    public AnalysisRunInformation createAnalysisRun(String projectId, Date startTime, long duration,
	    String description, FileSearchConfiguration fileSearchConfiguration) throws AnalysisStoreException {
	return analysisStore.createAnalysisRun(projectId, startTime, duration, description, fileSearchConfiguration);
    }
}
