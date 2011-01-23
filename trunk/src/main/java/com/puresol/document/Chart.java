package com.puresol.document;

import com.puresol.rendering.ChartRenderer;

/**
 * This object keeps the information about a single table element within the
 * document.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart extends AbstractDocumentPart {

	private static final long serialVersionUID = -3314964484840281581L;

	private final ChartRenderer chartRenderer;

	private String caption = "";

	public Chart(AbstractDocumentPart parent, String name,
			ChartRenderer chartRenderer) {
		super(parent, name);
		this.chartRenderer = chartRenderer;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public ChartRenderer getChartRenderer() {
		return chartRenderer;
	}

}
