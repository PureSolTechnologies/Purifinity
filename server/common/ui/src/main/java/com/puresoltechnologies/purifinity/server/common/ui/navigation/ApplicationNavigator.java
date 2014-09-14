package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import java.util.List;

/**
 * This is the interface for an application navigator. The application navigator
 * handles the organization of the pages, their hierarchy and also their
 * extensibility.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface ApplicationNavigator {

	/**
	 * This method returns the root element of the navigation hierarchy. This is
	 * in most cases the "home" item where the start of the application can be
	 * found.
	 * 
	 * @return A {@link NavigationItem} is returned.
	 */
	public NavigationItem getRootNode();

	/**
	 * This method returns the children of an item.
	 * 
	 * @param item
	 * @return
	 */
	public List<NavigationItem> getChildItems(NavigationItem item);

	/**
	 * This method returns the items for the bread crumb menu.
	 * 
	 * @param item
	 * @return
	 */
	public List<NavigationItem> getBreadcrumbItems(NavigationItem item);

	/**
	 * Adds a new navigation item.
	 * 
	 * @param path
	 * @param item
	 */
	public void addItem(String path, NavigationItem item);
}
