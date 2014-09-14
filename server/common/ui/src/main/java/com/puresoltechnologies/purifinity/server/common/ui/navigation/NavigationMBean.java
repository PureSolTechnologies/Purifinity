package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class NavigationMBean {

	@Inject
	private ApplicationNavigator applicationNavigator;

	private NavigationItem location;

	@PostConstruct
	public void initialize() {
		location = applicationNavigator.getRootNode();
	}

	public NavigationItem getLocation() {
		return location;
	}
}
