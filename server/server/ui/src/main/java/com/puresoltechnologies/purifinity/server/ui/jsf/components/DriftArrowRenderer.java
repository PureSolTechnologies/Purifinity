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
	String color = driftArrow.getColor();
	ResponseWriter responseWriter = context.getResponseWriter();

	responseWriter.startElement("svg", component);
	responseWriter.writeAttribute("xmlns", "http://www.w3.org/2000/svg",
		null);
	responseWriter.writeAttribute("version", "1.1", null);
	responseWriter.writeAttribute("height", "2em", "height");
	responseWriter.writeAttribute("width", "2em", "width");
	responseWriter.writeAttribute("viewBox", "0 0 101 101", null);

	responseWriter.startElement("circle", component);
	responseWriter.writeAttribute("cx", "50", null);
	responseWriter.writeAttribute("cy", "50", null);
	responseWriter.writeAttribute("r", "50", null);
	responseWriter.writeAttribute("stroke", "darkgray", null);
	responseWriter.writeAttribute("fill", color, null);
	responseWriter.writeAttribute("stroke-width", "1", null);
	responseWriter.endElement("circle");

	// horizontal
	responseWriter.startElement("line", component);
	responseWriter.writeAttribute("x1", "0", null);
	responseWriter.writeAttribute("y1", "50", null);
	responseWriter.writeAttribute("x2", "100", null);
	responseWriter.writeAttribute("y2", "50", null);
	responseWriter.writeAttribute("stroke", "black", null);
	responseWriter.writeAttribute("stroke-width", "1", null);
	responseWriter.endElement("line");
	// vertical
	responseWriter.startElement("line", component);
	responseWriter.writeAttribute("x1", "50", null);
	responseWriter.writeAttribute("y1", "0", null);
	responseWriter.writeAttribute("x2", "50", null);
	responseWriter.writeAttribute("y2", "100", null);
	responseWriter.writeAttribute("stroke", "black", null);
	responseWriter.writeAttribute("stroke-width", "1", null);
	responseWriter.endElement("line");

	int pos = (int) Math.round(Math.sqrt(50.0 * 50.0 / 2.0));
	// upper-left to lower-right
	responseWriter.startElement("line", component);
	responseWriter.writeAttribute("x1", String.valueOf(50 - pos), null);
	responseWriter.writeAttribute("y1", String.valueOf(50 - pos), null);
	responseWriter.writeAttribute("x2", String.valueOf(50 + pos), null);
	responseWriter.writeAttribute("y2", String.valueOf(50 + pos), null);
	responseWriter.writeAttribute("stroke", "black", null);
	responseWriter.writeAttribute("stroke-width", "1", null);
	responseWriter.endElement("line");

	// lower-left to upper-right
	responseWriter.startElement("line", component);
	responseWriter.writeAttribute("x1", String.valueOf(50 - pos), null);
	responseWriter.writeAttribute("y1", String.valueOf(50 + pos), null);
	responseWriter.writeAttribute("x2", String.valueOf(50 + pos), null);
	responseWriter.writeAttribute("y2", String.valueOf(50 - pos), null);
	responseWriter.writeAttribute("stroke", "black", null);
	responseWriter.writeAttribute("stroke-width", "1", null);
	responseWriter.endElement("line");

	responseWriter.startElement("g", component);
	responseWriter.writeAttribute("transform", "rotate(" + (-1 * deg)
		+ ",50,50)", "degree");

	int x = (int) Math.round(50 - Math.sqrt(50 * 50 - 17 * 17));

	responseWriter.startElement("path", component);
	responseWriter.writeAttribute("d", "M " + x
		+ " 33 50 33 50 10 100 50 50 90 50 67 " + x + " 67 " + x
		+ " 33 ", null);
	responseWriter.writeAttribute("stroke", "black", null);
	responseWriter.writeAttribute("fill", "black", null);

	responseWriter.endElement("path");
	responseWriter.endElement("g");
	responseWriter.endElement("svg");
    }

}
