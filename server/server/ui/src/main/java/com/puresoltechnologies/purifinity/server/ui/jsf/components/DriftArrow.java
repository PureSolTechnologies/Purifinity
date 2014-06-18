package com.puresoltechnologies.purifinity.server.ui.jsf.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

@FacesComponent(value = DriftArrow.COMPONENT_TYPE, createTag = true, tagName = "driftArrow", namespace = "http://puresoltechnologies.com/jsf")
public class DriftArrow extends UIOutput {

    public static final String COMPONENT_TYPE = "com.puresoltechnologies.purifinity.jsf.DriftArrow";
    public static final String COMPONENT_FAMILY = "com.puresoltechnologies.purifinity.jsf.output";
    public static final String RENDERER_TYPE = COMPONENT_TYPE;

    enum PropertyKeys {
	DEG, COLOR
    }

    public DriftArrow() {
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

    public int getDeg() {
	return (Integer) getStateHelper().eval(PropertyKeys.DEG, 0);
    }

    public void setDeg(int deg) {
	getStateHelper().put(PropertyKeys.DEG, deg);
    }

    public String getColor() {
	return (String) getStateHelper().eval(PropertyKeys.COLOR, "#ffffff");
    }

    public void setColor(String color) {
	getStateHelper().put(PropertyKeys.COLOR, color);
    }

}
