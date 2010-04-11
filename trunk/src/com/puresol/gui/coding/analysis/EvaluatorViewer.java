package com.puresol.gui.coding.analysis;

import java.io.File;
import java.util.Hashtable;

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

import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.reporting.ReportingFormat;

public class EvaluatorViewer extends BorderLayoutWidget {

	private static final long serialVersionUID = 7729851519489273274L;

	private static final Logger logger = Logger
			.getLogger(EvaluatorViewer.class);
	private static final Translator translator = Translator
			.getTranslator(EvaluatorViewer.class);

	private final TextField evaluatorName = new TextField();
	private final HTMLTextPane description = new HTMLTextPane();
	private final HTMLTextPane projectSummary = new HTMLTextPane();
	private final FreeList fileList = new FreeList();
	private final HTMLTextPane evaluatorFileComment = new HTMLTextPane();

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
		Panel filePanel = new Panel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
		tabbedPane.add(filePanel, translator.i18n("Evaluators File Summary"));

		filePanel.add(new ScrollPane(fileList));
		fileList.connect("valueChanged", this, "fileChanged", Object.class);
		filePanel.add(new ScrollPane(evaluatorFileComment));
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
		evaluatorFileComment.setText("");
		refreshFileList();
	}

	private void refreshFileList() {
		fileList.removeAll();
		Hashtable<Object, Object> listData = new Hashtable<Object, Object>();
		for (File file : evaluator.getFiles()) {
			listData.put(file.getName(), file);
		}
		fileList.setListData(listData);
	}

	@Slot
	public void fileChanged(Object o) {
		File file = (File) o;
		System.out.println(file);
		try {
			evaluatorFileComment.setText(evaluator.getFileComment(file,
					ReportingFormat.HTML));
		} catch (UnsupportedReportingFormatException e) {
			evaluatorFileComment.setText(translator.i18n(
					"Evaluator does not (yet) support {0} reporting format.",
					ReportingFormat.HTML));
			logger.warn(e.getMessage(), e);
		}
	}
}
