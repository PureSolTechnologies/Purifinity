package com.puresoltechnologies.purifinity.server.ui.jsf.components;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily = Plot2D.COMPONENT_FAMILY, rendererType = Plot2D.COMPONENT_TYPE)
public class Plot2DRenderer extends Renderer {

    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
	    throws IOException {
	Plot2D plot2D = (Plot2D) component;

	ResponseWriter responseWriter = context.getResponseWriter();
	responseWriter.startElement("canvas", component);
	responseWriter.writeAttribute("class", "plot2D", null);
	responseWriter.writeAttribute("id", component.getId(), null);
	responseWriter.writeAttribute("width", "618", null);
	responseWriter.writeAttribute("height", "382", null);
	responseWriter.endElement("canvas");
	responseWriter.startElement("script", component);
	responseWriter.writeAttribute("type", "text/javascript", null);
	responseWriter.writeText("var plot = new Plot('" + component.getId()
		+ "');\n" + "plots.push(plot);\n" + "plot.render();",
		component, null);
	responseWriter.endElement("script");
    }
}
