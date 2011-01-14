package com.puresol.gui.coding;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.puresol.coding.evaluator.Evaluator;
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.HTMLTextPane;

/**
 * This class visualizes the results list from an evaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ReportPanel extends JPanel {

	private static final long serialVersionUID = -8548150829784345153L;

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
		html.setText(new HTMLConverter(evaluator.getReport()).toString());
	}

	public Evaluator getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(Evaluator evaluator) {
		this.evaluator = evaluator;
		refresh();
	}

}
