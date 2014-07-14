package com.puresoltechnologies.purifinity.server.ui.jsf.components;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

@FacesComponent(value = Plot2D.COMPONENT_TYPE, createTag = true, tagName = "plot2D", namespace = "http://puresoltechnologies.com/jsf")
@ResourceDependencies({ @ResourceDependency(name = "head.js", library = "js"),
	@ResourceDependency(name = "plot.js", library = "js"),
	@ResourceDependency(name = "plot.css", library = "css") })
public class Plot2D extends UIOutput {

    public static final String COMPONENT_TYPE = "com.puresoltechnologies.purifinity.jsf.Plot2D";
    public static final String COMPONENT_FAMILY = "com.puresoltechnologies.purifinity.jsf.output";
    public static final String RENDERER_TYPE = COMPONENT_TYPE;

    enum PropertyKeys {
	DEG, COLOR
    }

    public Plot2D() {
	setRendererType(RENDERER_TYPE);
    }

    @Override
    public String getFamily() {
	return COMPONENT_FAMILY;
    }

    @Override
    public String getRendererType() {
	return RENDERER_TYPE;
    }

}
