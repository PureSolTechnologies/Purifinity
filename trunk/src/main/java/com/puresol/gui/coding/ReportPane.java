package com.puresol.gui.coding;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swingx.HTMLTextPane;
import javax.swingx.Panel;

import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.Result;

/**
 * This class visualizes the results list from an evaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ReportPane extends Panel {

	private static final long serialVersionUID = -8548150829784345153L;

	private static final Translator translator = Translator
			.getTranslator(ReportPane.class);

	private Evaluator evaluator = null;
	private final QualityLabel quality = new QualityLabel();
	private final HTMLTextPane html = new HTMLTextPane();

	public ReportPane() {
		super();
		initUI();
	}

	public ReportPane(Evaluator evaluator) {
		super();
		initUI();
		setEvaluator(evaluator);
	}

	private void initUI() {
		setLayout(new BorderLayout());
		add(quality, BorderLayout.WEST);
		add(html, BorderLayout.CENTER);
	}

	private void refresh() {
		quality.setQuality(evaluator.getQuality());
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><body>");
		buffer.append("<h1>" + evaluator.getName() + "</h1>");
		buffer.append("<h2>" + translator.i18n("Description") + "</h2>");
		buffer.append("<p>" + evaluator.getDescription() + "</p>");
		buffer.append("<h2>" + translator.i18n("Results") + "</h2>");
		buffer.append("<table border=\"1\">");
		buffer.append("<tr>");
		buffer.append("<th>" + translator.i18n("Symbol") + "</th>");
		buffer.append("<th>" + translator.i18n("Value") + "</th>");
		buffer.append("<th>" + translator.i18n("Unit") + "</th>");
		buffer.append("<th>" + translator.i18n("Description") + "</th>");
		buffer.append("</tr>");
		for (Result result : evaluator.getResults()) {
			buffer.append("<tr>");
			buffer.append("<td>" + result.getName() + "</td>");
			buffer.append("<td>" + result.getValue() + "</td>");
			buffer.append("<td>" + result.getUnit() + "</td>");
			buffer.append("<td>" + result.getDescription() + "</td>");
			buffer.append("</tr>");
		}
		buffer.append("</table></body></html>");
		html.setText(buffer.toString());
	}

	public Evaluator getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(Evaluator evaluator) {
		this.evaluator = evaluator;
		refresh();
	}

}
