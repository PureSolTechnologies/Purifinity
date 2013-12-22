package com.puresoltechnologies.purifinity.framework.analysis.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;

public class AnalysisProjectImpl implements AnalysisProject {

	private static final long serialVersionUID = 461507961936256914L;

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisProjectImpl.class);

	public static AnalysisProject open(UUID uuid) throws AnalysisStoreException {
		AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
				.getInstance();
		return analysisStore.loadAnalysis(uuid);
	}

	public static AnalysisProject create(AnalysisProjectSettings settings)
			throws AnalysisStoreException {
		AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
				.getInstance();
		return analysisStore.createAnalysis(settings);
	}

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
			analysisStore.updateSettings(uuid, settings);
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
	public AnalysisRun createAnalysisRun() throws AnalysisProjectException {
		try {
			return analysisStore.createAnalysisRun(this.uuid);
		} catch (AnalysisStoreException e) {
			logger.error("Could not create analysis run.", e);
			throw new AnalysisProjectException("Could not create analysis run.");
		}
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
