package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

/**
 * This is an implementation of an {@link ApplicationNavigator}.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
@Singleton
public class ApplicationNavigatorBean implements ApplicationNavigator {

    private final Map<String, NavigationItem> idMap = new HashMap<>();

    private NavigationItem rootNode;

    @PostConstruct
    public void initialize() {
	addItem(null, new NavigationItem(
		"com.puresoltechnologies.purifinity.index", "Purifinity",
		"/index"));

	addItem("com.puresoltechnologies.purifinity.index", new NavigationItem(
		"com.puresoltechnologies.purifinity.dashboards.index",
		"Dashboards", "/dashboards/index"));
	addItem("com.puresoltechnologies.purifinity.index", new NavigationItem(
		"com.puresoltechnologies.purifinity.projects.index",
		"Projects", "/projects/index"));
	addItem("com.puresoltechnologies.purifinity.index", new NavigationItem(
		"com.puresoltechnologies.purifinity.system.index", "System",
		"/system/index"));

	addItem("com.puresoltechnologies.purifinity.system.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.system.information",
			"Information", "/system/information"));
	addItem("com.puresoltechnologies.purifinity.system.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.system.installed_plugins",
			"Installed Plugins", "/system/installed_plugins"));
	addItem("com.puresoltechnologies.purifinity.system.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.system.graph_database",
			"Graph Database", "/system/graph_database"));

	addItem("com.puresoltechnologies.purifinity.index", new NavigationItem(
		"com.puresoltechnologies.purifinity.settings.index",
		"Settings", "/settings/index"));
	addItem("com.puresoltechnologies.purifinity.settings.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.projects",
			"Projects", "/settings/projects"));
	addItem("com.puresoltechnologies.purifinity.settings.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.dashboards",
			"Dashboards", "/settings/dashboards"));
	addItem("com.puresoltechnologies.purifinity.settings.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.defaults",
			"Defaults", "/settings/defaults"));
	addItem("com.puresoltechnologies.purifinity.settings.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.users",
			"Users", "/settings/users"));
	addItem("com.puresoltechnologies.purifinity.settings.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.plugins",
			"Plugins", "/settings/plugins"));

	addItem("com.puresoltechnologies.purifinity.settings.plugins",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.plugins.analyzers",
			"Analyzers", "/settings/analyzers"));
	addItem("com.puresoltechnologies.purifinity.settings.plugins",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.plugins.evaluators",
			"Evaluators", "/settings/evaluators"));

	addItem("com.puresoltechnologies.purifinity.settings.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.system",
			"System", "/settings/system"));
	addItem("com.puresoltechnologies.purifinity.settings.index",
		new NavigationItem(
			"com.puresoltechnologies.purifinity.settings.server",
			"Server", "/settings/server"));
    }

    @Override
    public NavigationItem getRootNode() {
	return rootNode;
    }

    @Override
    public List<NavigationItem> getChildItems(String id) {
	return getChildItems(findItemById(id));
    }

    @Override
    public List<NavigationItem> getChildItems(NavigationItem item) {
	if (item == null) {
	    return new ArrayList<>();
	}
	return item.getChildren();
    }

    @Override
    public List<NavigationItem> getBreadcrumbItems(String id) {
	return getBreadcrumbItems(findItemById(id));
    }

    @Override
    public List<NavigationItem> getBreadcrumbItems(NavigationItem item) {
	List<NavigationItem> items = new ArrayList<>();
	NavigationItem currentItem = item;
	while (currentItem != null) {
	    items.add(0, currentItem);
	    currentItem = currentItem.getParent();
	}
	if (items.isEmpty()) {
	    items.add(rootNode);
	}
	return items;
    }

    @Override
    public void addItem(String id, NavigationItem item) {
	String itemId = item.getId();
	if (idMap.containsKey(itemId)) {
	    throw new IllegalStateException("An item with id '" + itemId
		    + "' already exists.");
	}
	if (id == null) {
	    if (rootNode != null) {
		throw new IllegalStateException("Root node was set already!");
	    }
	    rootNode = item;
	} else {
	    NavigationItem parentItem = findItemById(id);
	    if (parentItem == null) {
		throw new IllegalStateException("A parent item with id '" + id
			+ "' does not exists.");
	    }
	    item.setParent(parentItem);
	    parentItem.getChildren().add(item);
	}
	idMap.put(itemId, item);
    }

    @Override
    public NavigationItem findItemById(String id) {
	return idMap.get(id);
    }

}
