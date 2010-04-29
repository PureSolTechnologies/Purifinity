package com.puresol.gui.coding.analysis;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import javax.i18n4j.Translator;
import javax.swing.BoxLayout;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.FreeList;
import javax.swingx.HTMLTextPane;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.TabbedPane;
import javax.swingx.TextField;
import javax.swingx.connect.Slot;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;

public class EvaluatorViewer extends BorderLayoutWidget {

    private static final long serialVersionUID = 7729851519489273274L;

    private static final Logger logger = Logger
	    .getLogger(EvaluatorViewer.class);
    private static final Translator translator = Translator
	    .getTranslator(EvaluatorViewer.class);

    private final TextField evaluatorName = new TextField();
    private final HTMLTextPane description = new HTMLTextPane();
    private final HTMLTextPane projectSummary = new HTMLTextPane();
    private final FreeList codeRangeList = new FreeList();
    private final HTMLTextPane evaluatorCodeRangeComment = new HTMLTextPane();

    private Evaluator evaluator = null;

    public EvaluatorViewer() {
	super();
	initUI();
    }

    public EvaluatorViewer(Evaluator evaluator) {
	super();
	initUI();
	setEvaluator(evaluator);
    }

    private void initUI() {
	setNorth(evaluatorName);
	TabbedPane tabbedPane = new TabbedPane();
	setCenter(tabbedPane);
	tabbedPane.add(new ScrollPane(description), translator
		.i18n("Evaluator Description"));
	tabbedPane.add(new ScrollPane(projectSummary), translator
		.i18n("Project Summary"));
	Panel codeRangePanel = new Panel();
	codeRangePanel
		.setLayout(new BoxLayout(codeRangePanel, BoxLayout.X_AXIS));
	tabbedPane.add(codeRangePanel, translator
		.i18n("Evaluators Code Range Summary"));

	codeRangePanel.add(new ScrollPane(codeRangeList));
	codeRangeList.connect("valueChanged", this, "codeRangeChanged",
		Object.class);
	codeRangePanel.add(new ScrollPane(evaluatorCodeRangeComment));
    }

    /**
     * @return the evaluator
     */
    public Evaluator getEvaluator() {
	return evaluator;
    }

    /**
     * @param evaluator
     *            the evaluator to set
     */
    public void setEvaluator(Evaluator evaluator) {
	this.evaluator = evaluator;
	refresh();
    }

    private void refresh() {
	evaluatorName.setText(evaluator.getName());
	try {
	    description.setText(evaluator.getDescription(ReportingFormat.HTML));
	} catch (UnsupportedReportingFormatException e) {
	    description.setText(translator.i18n(
		    "Evaluator does not (yet) support {0} reporting format.",
		    ReportingFormat.HTML));
	    logger.warn(e.getMessage(), e);
	}

	try {
	    projectSummary.setText(evaluator
		    .getProjectComment(ReportingFormat.HTML));
	} catch (UnsupportedReportingFormatException e) {
	    projectSummary.setText(translator.i18n(
		    "Evaluator does not (yet) support {0} reporting format.",
		    ReportingFormat.HTML));
	    logger.warn(e.getMessage(), e);
	}
	evaluatorCodeRangeComment.setText("");
	refreshCodeRangeList();
    }

    private void refreshCodeRangeList() {
	codeRangeList.removeAll();
	Map<Object, Object> codeRanges = new Hashtable<Object, Object>();
	for (File file : evaluator.getFiles()) {
	    for (CodeRange codeRange : evaluator.getCodeRanges(file)) {
		String text = "<html>";
		QualityLevel level = evaluator.getQuality(codeRange);
		if (level == QualityLevel.HIGH) {
		    text += "<font color=\"#00ff00\">";
		} else if (level == QualityLevel.MEDIUM) {
		    text += "<font color=\"#c0c000\">";

		} else if (level == QualityLevel.LOW) {
		    text += "<font color=\"#ff0000\">";
		} else {
		    text += "<font color=\"#000000\">";
		}
		text += HTMLStandards.convertFlowTextToHTML(codeRange
			.toString());
		text += "</font>";
		text += "</html>";
		codeRanges.put(text, codeRange);
	    }
	}
	codeRangeList.setListData(codeRanges);
    }

    @Slot
    public void codeRangeChanged(Object o) {
	CodeRange codeRange = (CodeRange) o;
	System.out.println(codeRange);
	try {
	    evaluatorCodeRangeComment.setText(evaluator.getCodeRangeComment(
		    codeRange, ReportingFormat.HTML));
	} catch (UnsupportedReportingFormatException e) {
	    evaluatorCodeRangeComment.setText(translator.i18n(
		    "Evaluator does not (yet) support {0} reporting format.",
		    ReportingFormat.HTML));
	    logger.warn(e.getMessage(), e);
	}
    }
}
