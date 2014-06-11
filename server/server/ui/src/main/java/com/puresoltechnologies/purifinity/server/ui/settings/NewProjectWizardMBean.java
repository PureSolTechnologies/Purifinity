package com.puresoltechnologies.purifinity.server.ui.settings;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesDefaults;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesNames;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesValue;

@ViewScoped
@ManagedBean
public class NewProjectWizardMBean {

	@Inject
	private Logger logger;

	@Inject
	private RepositoryTypePluginService repositoryTypePluginService;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private PreferencesStore preferencesStore;

	private String projectName = null;
	private String projectDescription = null;
	private String repositoryTypeClass = null;
	private RepositoryType repositoryType = null;

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

	public RepositoryTypePluginService getRepositoryTypePluginService() {
		return repositoryTypePluginService;
	}

	public void setRepositoryTypePluginService(
			RepositoryTypePluginService repositoryTypePluginService) {
		this.repositoryTypePluginService = repositoryTypePluginService;
	}

	public String getRepositoryTypeClass() {
		return repositoryTypeClass;
	}

	public void setRepositoryTypeClass(String repositoryTypeClass) {
		this.repositoryTypeClass = repositoryTypeClass;
		repositoryType = findRepositoryTypeForClass(repositoryTypeClass);
	}

	private RepositoryType findRepositoryTypeForClass(String repositoryTypeClass) {
		for (RepositoryType repositoryType : repositoryTypePluginService
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
		for (RepositoryType repositoryType : repositoryTypePluginService
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
			// FIXME: Set properties for repository.
			// for (String parameterId : controls.keySet()) {
			// Text text = controls.get(parameterId);
			// properties.setProperty(parameterId, text.getText());
			// }
			// FIXME: Add configuration for file search
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
		PreferencesValue directoryIncludesPreference = preferencesStore
				.getValue(
						PreferencesNames.DEFAULT_GROUP,
						PreferencesNames.ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES,
						PreferencesDefaults.ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES);
		PreferencesValue directoryExcludesPreference = preferencesStore
				.getValue(
						PreferencesNames.DEFAULT_GROUP,
						PreferencesNames.ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES,
						PreferencesDefaults.ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES);
		PreferencesValue fileIncludesPreference = preferencesStore.getValue(
				PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_FILE_INCLUDES,
				PreferencesDefaults.ANALYSIS_FILE_FILTER_FILE_INCLUDES);
		PreferencesValue fileExcludesPreference = preferencesStore.getValue(
				PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_FILE_EXCLUDES,
				PreferencesDefaults.ANALYSIS_FILE_FILTER_FILE_EXCLUDES);
		PreferencesValue ignoreHiddenPreference = preferencesStore.getValue(
				PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_IGNORE_HIDDEN,
				PreferencesDefaults.ANALYSIS_FILE_FILTER_IGNORE_HIDDEN);

		List<String> directoryIncludes = Arrays
				.asList(directoryIncludesPreference.getValue().split("\n"));
		List<String> directoryExcludes = Arrays
				.asList(directoryExcludesPreference.getValue().split("\n"));
		List<String> fileIncludes = Arrays.asList(fileIncludesPreference
				.getValue().split("\n"));
		List<String> fileExcludes = Arrays.asList(fileExcludesPreference
				.getValue().split("\n"));

		Boolean ignoreHidden = Boolean.valueOf(ignoreHiddenPreference
				.getValue());
		return new FileSearchConfiguration(directoryIncludes,
				directoryExcludes, fileIncludes, fileExcludes, ignoreHidden);
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
}
