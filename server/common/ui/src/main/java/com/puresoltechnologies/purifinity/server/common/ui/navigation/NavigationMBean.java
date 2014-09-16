package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class NavigationMBean implements Serializable {

    private static final long serialVersionUID = -5834575591704331878L;

    @Inject
    private ApplicationNavigator applicationNavigator;

    private NavigationItem location;

    @PostConstruct
    public void initialize() {
	location = applicationNavigator.getRootNode();
    }

    public String navigateTo(String id) {
	NavigationItem item = applicationNavigator.findItemById(id);
	if (item == null) {
	    throw new IllegalStateException(
		    "Could not find navigation item with id '" + id + "'.");
	}
	location = item;
	if (!item.isInternal()) {
	    redirect(item);
	}
	return item.getNavigationTarget();
    }

    private void redirect(NavigationItem item) {
	try {
	    ExternalContext ec = FacesContext.getCurrentInstance()
		    .getExternalContext();
	    ec.redirect(item.getNavigationTarget());
	} catch (IOException e) {
	    throw new RuntimeException("Could navigate to URL '"
		    + item.getNavigationTarget() + "'", e);
	}
    }

    public NavigationItem getLocation() {
	return location;
    }

    public List<NavigationItem> getChildItems(String name) {
	NavigationItem currentItem = applicationNavigator.findItemById(name);
	return getChildItems(currentItem);
    }

    public List<NavigationItem> getChildItems(NavigationItem item) {
	return applicationNavigator.getChildItems(item);
    }

    public List<NavigationItem> getBreadcrumbItems(String name) {
	NavigationItem currentItem = applicationNavigator.findItemById(name);
	return getBreadcrumbItems(currentItem);
    }

    public List<NavigationItem> getBreadcrumbItems(NavigationItem item) {
	return applicationNavigator.getBreadcrumbItems(item);
    }
}
