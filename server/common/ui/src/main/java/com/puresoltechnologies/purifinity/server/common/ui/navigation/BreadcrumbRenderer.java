package com.puresoltechnologies.purifinity.server.common.ui.navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily = Breadcrumb.COMPONENT_FAMILY, rendererType = Breadcrumb.COMPONENT_TYPE)
public class BreadcrumbRenderer extends Renderer {

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		Breadcrumb breadcrumb = (Breadcrumb) component;

		String pathInfo = context.getExternalContext().getRequestPathInfo();

		NavigationItem location = breadcrumb.getLocation();
		if (location != null) {
			List<NavigationItem> breadcrumbItems = new ArrayList<>();
			NavigationItem currentPosition = location;
			while (currentPosition != null) {
				breadcrumbItems.add(0, currentPosition);
				currentPosition = currentPosition.getParent();
			}

			ResponseWriter responseWriter = context.getResponseWriter();

			if (pathInfo != null) {
				responseWriter.writeText(pathInfo, null);
			}

			boolean first = true;
			for (NavigationItem item : breadcrumbItems) {
				if (first) {
					first = false;
				} else {
					responseWriter.writeText(" &gt; ", null);
				}
				responseWriter.startElement("a", component);
				responseWriter.writeAttribute("href", item.getUrl(), null);
				responseWriter.writeText(item.getName(), null);
				responseWriter.endElement("a");
			}
		}
	}

}
