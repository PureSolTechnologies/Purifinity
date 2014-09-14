package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import javax.faces.component.FacesComponent;
import javax.faces.component.StateHelper;
import javax.faces.component.UICommand;

@FacesComponent(value = Breadcrumb.COMPONENT_TYPE, createTag = true, tagName = "breadcrumb", namespace = "http://puresoltechnologies.com/jsf")
public class Breadcrumb extends UICommand {

	public static final String COMPONENT_TYPE = "com.puresoltechnologies.purifinity.jsf.breadcrumb";
	public static final String COMPONENT_FAMILY = "com.puresoltechnologies.purifinity.jsf.menu";
	public static final String RENDERER_TYPE = COMPONENT_TYPE;

	enum PropertyKeys {
		location;
	}

	public Breadcrumb() {
		setRendererType(RENDERER_TYPE);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public NavigationItem getLocation() {
		StateHelper stateHelper = getStateHelper();
		return (NavigationItem) stateHelper.eval(PropertyKeys.location);
	}

	public void setLocation(NavigationItem location) {
		StateHelper stateHelper = getStateHelper();
		stateHelper.put(PropertyKeys.location, location);
	}

}
