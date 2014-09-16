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
     * This method returns the children of an item.
     * 
     * @param id
     * @return
     */
    public List<NavigationItem> getChildItems(String id);

    /**
     * This method returns the items for the bread crumb menu.
     * 
     * @param item
     * @return
     */
    public List<NavigationItem> getBreadcrumbItems(NavigationItem item);

    /**
     * This method returns the items for the bread crumb menu.
     * 
     * @param id
     * @return
     */
    public List<NavigationItem> getBreadcrumbItems(String id);

    /**
     * Adds a new navigation item.
     * 
     * @param id
     *            is the id of the item which is the future parent of the new
     *            item.
     * @param item
     *            is the item to be added.
     */
    public void addItem(String id, NavigationItem item);

    /**
     * This method finds a certain item in the navigation tree by its id.
     * 
     * @param id
     *            is the id of the navigation item.
     * @return A {@link NavigationItem} is returned.
     */
    public NavigationItem findItemById(String id);

}
