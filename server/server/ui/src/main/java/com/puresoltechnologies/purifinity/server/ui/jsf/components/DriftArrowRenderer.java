package com.puresoltechnologies.purifinity.server.ui.jsf.components;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily = DriftArrow.COMPONENT_FAMILY, rendererType = DriftArrow.COMPONENT_TYPE)
public class DriftArrowRenderer extends Renderer {

    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
	    throws IOException {
	DriftArrow driftArrow = (DriftArrow) component;
	int deg = driftArrow.getDeg();
	ResponseWriter responseWriter = context.getResponseWriter();

	responseWriter.startElement("svg", component);
	responseWriter.writeAttribute("xmlns", "http://www.w3.org/2000/svg",
		null);
	responseWriter.writeAttribute("version", "1.1", null);
	responseWriter.writeAttribute("height", "2em", "height");
	responseWriter.writeAttribute("width", "2em", "width");
	responseWriter.writeAttribute("viewBox", "0 0 100 100", null);

	responseWriter.startElement("g", component);
	responseWriter.writeAttribute("transform", "rotate(" + deg + ",50,50)",
		"degree");

	responseWriter.startElement("path", component);
	responseWriter.writeAttribute("d",
		"M 10 33 50 33 50 10 100 50 50 90 50 67 10 67 10 33 ", null);
	responseWriter.writeAttribute("stroke", "red", null);
	responseWriter.writeAttribute("fill", "yellow", null);
	// TODO

	responseWriter.endElement("path");
	responseWriter.endElement("g");
	responseWriter.endElement("svg");
    }

}
