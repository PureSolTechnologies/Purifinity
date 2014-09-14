package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

	public NavigationItem rootNode;

	@PostConstruct
	public void initialize() {
		try {
			rootNode = new NavigationItem(null, "Purifinity", new URL(
					"http://localhost:8080/index.jsf"));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public NavigationItem getRootNode() {
		return rootNode;
	}

	@Override
	public List<NavigationItem> getChildItems(NavigationItem item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NavigationItem> getBreadcrumbItems(NavigationItem item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addItem(String path, NavigationItem item) {
		// TODO Auto-generated method stub

	}

}
