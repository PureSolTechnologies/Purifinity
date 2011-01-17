package com.puresol.document;

import org.jfree.chart.JFreeChart;

/**
 * This object keeps the information about a single table element within the
 * document.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart extends AbstractDocumentPart {

	private static final long serialVersionUID = -3314964484840281581L;

	private final JFreeChart chart;

	private String caption = "";

	public Chart(AbstractDocumentPart parent, String name, JFreeChart chart) {
		super(parent, name);
		this.chart = chart;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public JFreeChart getChart() {
		return chart;
	}

}
