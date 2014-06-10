package com.puresoltechnologies.purifinity.server.ui.settings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@ViewScoped
@ManagedBean
public class NewProjectWizardMBean {

	@Inject
	private Logger logger;

	@Inject
	private RepositoryTypePluginService repositoryTypePluginService;

	@Inject
	private AnalysisStore analysisStore;

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
			analysisStore
					.createAnalysisProject(new AnalysisProjectSettings(
							projectName, projectDescription,
							new FileSearchConfiguration(
									new ArrayList<String>(),
									new ArrayList<String>(),
									new ArrayList<String>(),
									new ArrayList<String>(), true),
							repositoryLocation));
			logger.info("Project '" + projectName + "' created.");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
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

	public void delete(UUID uuid) {
		try {
			analysisStore.removeAnalysisProject(uuid);
			logger.info("Project '" + projectName + "' deleted.");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
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
