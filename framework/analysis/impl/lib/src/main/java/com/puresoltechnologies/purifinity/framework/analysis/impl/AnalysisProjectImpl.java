package com.puresoltechnologies.purifinity.framework.analysis.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunner;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;

public class AnalysisProjectImpl implements AnalysisProject {

	private static final long serialVersionUID = 461507961936256914L;

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisProjectImpl.class);

	private final UUID uuid;
	private final Date creationTime;
	private final AnalysisProjectSettings settings;
	private final AnalysisStore analysisStore;

	public AnalysisProjectImpl(UUID uuid, Date creationTime,
			AnalysisProjectSettings settings) {
		super();
		this.uuid = uuid;
		this.creationTime = creationTime;
		this.settings = settings;
		analysisStore = AnalysisStoreFactory.getFactory().getInstance();
	}

	@Override
	public AnalysisProjectInformation getInformation() {
		return new AnalysisProjectInformation(uuid, creationTime);
	}

	@Override
	public List<AnalysisRunInformation> getAllRunInformation()
			throws AnalysisProjectException {
		try {
			return analysisStore.getAllRunInformation(this.uuid);
		} catch (AnalysisStoreException e) {
			logger.error("Could not read run information.", e);
			throw new AnalysisProjectException(
					"Could not read run information.");
		}
	}

	@Override
	public AnalysisProjectSettings getSettings() {
		return settings;
	}

	@Override
	public void updateSettings(AnalysisProjectSettings settings)
			throws AnalysisProjectException {
		try {
			analysisStore.updateAnalysisProjectSettings(uuid, settings);
		} catch (AnalysisStoreException e) {
			logger.error("Could not update settings.", e);
			throw new AnalysisProjectException("Could not update settings.");
		}
	}

	@Override
	public AnalysisRun loadAnalysisRun(UUID uuid)
			throws AnalysisProjectException {
		try {
			return analysisStore.loadAnalysisRun(this.uuid, uuid);
		} catch (AnalysisStoreException e) {
			logger.error("Could not load analysis run.", e);
			throw new AnalysisProjectException("Could not load analysis run.");
		}
	}

	@Override
	public AnalysisRunner createAnalysisRunner() {
		return new AnalysisRunnerImpl(uuid);
	}

	@Override
	public AnalysisRun loadLastAnalysisRun() throws AnalysisProjectException {
		try {
			return analysisStore.loadLastAnalysisRun(uuid);
		} catch (AnalysisStoreException e) {
			logger.error("Could not load last analysis run.", e);
			throw new AnalysisProjectException(
					"Could not load last analysis run.");
		}
	}

	@Override
	public void removeAnalysisRun(UUID uuid) throws AnalysisProjectException {
		try {
			analysisStore.removeAnalysisRun(this.uuid, uuid);
		} catch (AnalysisStoreException e) {
			logger.error("Could not remove analysis run.", e);
			throw new AnalysisProjectException("Could not remove analysis run.");
		}
	}

}
