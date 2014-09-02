package com.puresoltechnologies.purifinity.server.ui.projects.evaluation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

@ViewScoped
@ManagedBean
public class ProjectsEvaluationViewMBean implements Serializable {

	private static final long serialVersionUID = -7591892843528146768L;

	@Inject
	private AnalysisStoreService analysisStoreService;

	@Inject
	private EvaluatorServiceManager evaluatorServiceManager;

	private TreeNode rootNode;
	private final List<SelectItem> metricSelection = new ArrayList<>();
	private AnalysisFileTree selectedFileTree;

	@PostConstruct
	public void initialize() {
		initializeFileTree();
		initializeMetricsList();
	}

	private void initializeFileTree() {
		try {
			Map<String, String> requestParameterMap = FacesContext
					.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String project = requestParameterMap.get("project");
			if (project == null) {
				return;
			}
			UUID projectUUID = UUID.fromString(project);
			String run = requestParameterMap.get("run");
			UUID runUUID = null;
			if (run == null) {
				AnalysisRunInformation lastAnalysisRun = analysisStoreService
						.readLastAnalysisRun(projectUUID);
				runUUID = lastAnalysisRun.getRunUUID();
			} else {
				runUUID = UUID.fromString(run);
			}
			AnalysisFileTree analysisFileTree = analysisStoreService
					.readAnalysisFileTree(projectUUID, runUUID);

			rootNode = new DefaultTreeNode("directory", analysisFileTree, null);
			addChildren(rootNode, analysisFileTree);
		} catch (AnalysisStoreException e) {
			throw new RuntimeException("Could not read project tree.", e);
		}
	}

	private void initializeMetricsList() {
		for (EvaluatorServiceInformation service : evaluatorServiceManager
				.getServices()) {
			if (service.getType() == EvaluatorType.METRICS) {
				List<SelectItem> parameterSelection = new ArrayList<>();
				for (Parameter<?> parameter : service.getParameters()) {
					parameterSelection.add(new SelectItem(parameter, parameter
							.getName(), parameter.getDescription()));
				}
				SelectItemGroup group = new SelectItemGroup(service.getName(),
						service.getDescription(), false,
						parameterSelection
								.toArray(new SelectItem[parameterSelection
										.size()]));
				metricSelection.add(group);
			}
		}
	}

	private void addChildren(TreeNode parent, AnalysisFileTree analysisFileTree) {
		List<AnalysisFileTree> children = analysisFileTree.getChildren();
		Collections.sort(children, new Comparator<AnalysisFileTree>() {
			@Override
			public int compare(AnalysisFileTree o1, AnalysisFileTree o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (AnalysisFileTree child : children) {
			if (child.isFile()) {
				new DefaultTreeNode("file", child, parent);
			} else {
				TreeNode childNode = new DefaultTreeNode("directory", child,
						parent);
				addChildren(childNode, child);
			}
		}
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public List<SelectItem> getAvailableMetrics() {
		return metricSelection;
	}

	public void onNodeSelect(NodeSelectEvent event) {
		TreeNode treeNode = event.getTreeNode();
		selectedFileTree = (AnalysisFileTree) treeNode.getData();
	}

	public AnalysisFileTree getSelectedFileTree() {
		return selectedFileTree;
	}
}
