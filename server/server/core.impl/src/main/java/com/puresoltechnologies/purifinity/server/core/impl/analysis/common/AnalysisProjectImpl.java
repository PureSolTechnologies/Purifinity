package com.puresoltechnologies.purifinity.server.core.impl.analysis.common;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
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

	public static AnalysisProjectImpl readFromStore(UUID uuid)
			throws AnalysisStoreException {
		AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
				.getInstance();
		AnalysisProjectInformation information = analysisStore
				.readAnalysisProjectInformation(uuid);
		AnalysisProjectSettings settings = analysisStore
				.readAnalysisProjectSettings(uuid);
		return new AnalysisProjectImpl(information, settings);
	}

	private final AnalysisProjectInformation information;
	private final AnalysisProjectSettings settings;
	private final AnalysisStore analysisStore;

	public AnalysisProjectImpl(UUID uuid, Date creationTime,
			AnalysisProjectSettings settings) {
		this(new AnalysisProjectInformation(uuid, creationTime), settings);
	}

	public AnalysisProjectImpl(AnalysisProjectInformation information,
			AnalysisProjectSettings settings) {
		super();
		this.information = information;
		this.settings = settings;
		analysisStore = AnalysisStoreFactory.getFactory().getInstance();
	}

	@Override
	public AnalysisProjectInformation getInformation() {
		return information;
	}

	@Override
	public List<AnalysisRunInformation> getAllRunInformation()
			throws AnalysisProjectException {
		try {
			return analysisStore.readAllRunInformation(information.getUUID());
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
			analysisStore.updateAnalysisProjectSettings(information.getUUID(),
					settings);
		} catch (AnalysisStoreException e) {
			logger.error("Could not update settings.", e);
			throw new AnalysisProjectException("Could not update settings.");
		}
	}

	@Override
	public AnalysisRunInformation loadAnalysisRun(UUID uuid)
			throws AnalysisProjectException {
		try {
			return analysisStore.readAnalysisRun(information.getUUID(), uuid);
		} catch (AnalysisStoreException e) {
			logger.error("Could not load analysis run.", e);
			throw new AnalysisProjectException("Could not load analysis run.");
		}
	}

	@Override
	public AnalysisRunInformation loadLastAnalysisRun()
			throws AnalysisProjectException {
		try {
			return analysisStore.readLastAnalysisRun(information.getUUID());
		} catch (AnalysisStoreException e) {
			logger.error("Could not load last analysis run.", e);
			throw new AnalysisProjectException(
					"Could not load last analysis run.");
		}
	}

	@Override
	public void removeAnalysisRun(UUID uuid) throws AnalysisProjectException {
		try {
			analysisStore.removeAnalysisRun(information.getUUID(), uuid);
		} catch (AnalysisStoreException e) {
			logger.error("Could not remove analysis run.", e);
			throw new AnalysisProjectException("Could not remove analysis run.");
		}
	}

	@Override
	public String toString() {
		return getSettings().getName()
				+ ": "
				+ RepositoryLocationCreator.createFromSerialization(
						getSettings().getRepositoryLocation())
						.getHumanReadableLocationString() + " ("
				+ getInformation().getUUID() + ")";
	}
}
