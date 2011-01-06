package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.i18n4java.Translator;
import javax.swingx.HTMLTextPane;
import javax.swingx.Panel;

import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.EvaluatorOutput;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.SourceCodeQuality;

/**
 * This class visualizes the results list from an evaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ReportPanel extends Panel {

	private static final long serialVersionUID = -8548150829784345153L;

	private static final Translator translator = Translator
			.getTranslator(ReportPanel.class);

	private Evaluator evaluator = null;
	private final QualityLabel quality = new QualityLabel();
	private final HTMLTextPane html = new HTMLTextPane();

	public ReportPanel() {
		super();
		initUI();
	}

	public ReportPanel(Evaluator evaluator) {
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
		if (evaluator.getResults() != null) {
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
			buffer.append("</table>");
		}
		if (evaluator.getPartQualities() != null) {
			buffer.append("<h2>" + translator.i18n("Part Qualities") + "</h2>");
			buffer.append("<table border=\"1\">");
			buffer.append("<tr>");
			buffer.append("<th>" + translator.i18n("Part Name") + "</th>");
			buffer.append("<th>" + translator.i18n("Quality") + "</th>");
			buffer.append("</tr>");
			Map<String, SourceCodeQuality> qualities = evaluator
					.getPartQualities();
			List<String> names = new ArrayList<String>(qualities.keySet());
			Collections.sort(names);
			for (String name : names) {
				SourceCodeQuality quality = qualities.get(name);
				buffer.append("<tr>");
				buffer.append("<td>" + name + "</td>");
				switch (quality) {
				case HIGH:
					buffer.append("<td bgcolor=\"green\">"
							+ quality.getIdentifier() + "</td>");
					break;
				case MEDIUM:
					buffer.append("<td bgcolor=\"yellow\">"
							+ quality.getIdentifier() + "</td>");
					break;
				case LOW:
					buffer.append("<td bgcolor=\"red\">"
							+ quality.getIdentifier() + "</td>");
					break;
				default:
					buffer.append("<td>" + quality.getIdentifier() + "</td>");
				}
				buffer.append("</tr>");
			}
			buffer.append("</table>");
		}
		if (evaluator.getTextOutput() != null) {
			buffer.append("<h2>" + translator.i18n("Evaluator Comments")
					+ "</h2>");
			for (EvaluatorOutput output : evaluator.getTextOutput()) {
				buffer.append("<h3>" + output.getSectionName() + "</h3>");
				buffer.append("<p>"
						+ output.getText().replace("\n\n", "</p><p>")
								.replaceAll("\n", "<br/>") + "</p>");
			}
		}
		buffer.append("</body></html>");
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
