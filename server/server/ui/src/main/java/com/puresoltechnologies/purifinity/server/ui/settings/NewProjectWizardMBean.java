package com.puresoltechnologies.purifinity.server.ui.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypeServiceManager;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesDefaults;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesNames;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesStore;

@ViewScoped
@ManagedBean
public class NewProjectWizardMBean implements Serializable {

	private static final long serialVersionUID = 3905897499431532258L;

	@Inject
	private Logger logger;

	@Inject
	private RepositoryTypeServiceManager repositoryTypePluginService;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private PreferencesStore preferencesStore;

	private String projectName = null;
	private String projectDescription = null;
	private String repositoryTypeClass = null;
	private RepositoryTypeServiceInformation repositoryType = null;
	private final List<RepositorySetting> repositorySettings = new ArrayList<>();

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getRepositoryTypeClass() {
		return repositoryTypeClass;
	}

	public void setRepositoryTypeClass(String repositoryTypeClass) {
		this.repositoryTypeClass = repositoryTypeClass;
		repositoryType = findRepositoryTypeForClass(repositoryTypeClass);
		repositorySettings.clear();
		if (repositoryType != null) {
			for (Entry<String, Parameter<?>> entry : repositoryType
					.getParameters().entrySet()) {
				repositorySettings.add(new RepositorySetting(entry.getKey(),
						entry.getValue()));
			}
		}
	}

	private RepositoryTypeServiceInformation findRepositoryTypeForClass(
			String repositoryTypeClass) {
		for (RepositoryTypeServiceInformation repositoryType : repositoryTypePluginService
				.getServices()) {
			if (repositoryType.getClassName().equals(repositoryTypeClass)) {
				return repositoryType;
			}
		}
		return null;
	}

	public String getSelectedRepositoryTypeName() {
		return repositoryType != null ? repositoryType.getName()
				: "not selected";
	}

	public Map<String, String> getRepositoryTypes() {
		Map<String, String> repositoryTypes = new LinkedHashMap<>();
		for (RepositoryTypeServiceInformation repositoryType : repositoryTypePluginService
				.getServices()) {
			String name = repositoryType.getName();
			String className = repositoryType.getClassName();
			repositoryTypes.put(name, className);
		}
		return repositoryTypes;
	}

	public void create() {
		try {
			Properties repositoryLocation = new Properties();
			repositoryLocation.put(
					RepositoryLocation.REPOSITORY_LOCATION_CLASS,
					repositoryTypeClass);
			repositoryLocation.setProperty(
					RepositoryLocation.REPOSITORY_LOCATION_NAME,
					repositoryType.getName());
			for (RepositorySetting repositorySetting : repositorySettings) {
				repositoryLocation.setProperty(repositorySetting.getParameter()
						.getName(), repositorySetting.getValue());
			}
			analysisStore.createAnalysisProject(new AnalysisProjectSettings(
					projectName, projectDescription,
					createFileSearchConfiguration(), repositoryLocation));
			logger.info("Project '" + projectName + "' created.");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Project created", "Project " + projectName + " ("
							+ projectDescription + ") was created.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (AnalysisStoreException e) {
			logger.error("Could not create project.", e);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error creating project", "Could not create project "
							+ projectName + " (" + projectDescription + ").");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	private FileSearchConfiguration createFileSearchConfiguration() {
		String directoryIncludesPreference = preferencesStore.getString(
				PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES,
				PreferencesDefaults.ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES);
		String directoryExcludesPreference = preferencesStore.getString(
				PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES,
				PreferencesDefaults.ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES);
		String fileIncludesPreference = preferencesStore.getString(
				PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_FILE_INCLUDES,
				PreferencesDefaults.ANALYSIS_FILE_FILTER_FILE_INCLUDES);
		String fileExcludesPreference = preferencesStore.getString(
				PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_FILE_EXCLUDES,
				PreferencesDefaults.ANALYSIS_FILE_FILTER_FILE_EXCLUDES);
		boolean ignoreHiddenPreference = preferencesStore.getBoolean(
				PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_IGNORE_HIDDEN,
				PreferencesDefaults.ANALYSIS_FILE_FILTER_IGNORE_HIDDEN);

		List<String> directoryIncludes = Arrays
				.asList(directoryIncludesPreference.split("\n"));
		List<String> directoryExcludes = Arrays
				.asList(directoryExcludesPreference.split("\n"));
		List<String> fileIncludes = Arrays.asList(fileIncludesPreference
				.split("\n"));
		List<String> fileExcludes = Arrays.asList(fileExcludesPreference
				.split("\n"));

		return new FileSearchConfiguration(directoryIncludes,
				directoryExcludes, fileIncludes, fileExcludes,
				ignoreHiddenPreference);
	}

	public void delete(UUID uuid) {
		try {
			analysisStore.removeAnalysisProject(uuid);
			logger.info("Project '" + projectName + "' deleted.");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Project deleted", "Project " + projectName + " ("
							+ projectDescription + ") was deleted.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (AnalysisStoreException e) {
			logger.error("Could not delete project.", e);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error deleting project", "Could not delete project "
							+ projectName + " (" + projectDescription + ").");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
	}

	public List<RepositorySetting> getRepositorySettings() {
		return repositorySettings;
	}

}
