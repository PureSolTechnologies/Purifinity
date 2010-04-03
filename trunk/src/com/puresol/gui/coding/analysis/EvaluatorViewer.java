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

import com.puresol.coding.evaluator.Evaluator;
import com.puresol.reporting.ReportingFormat;

public class EvaluatorViewer extends BorderLayoutWidget {

	private static final long serialVersionUID = 7729851519489273274L;

	private static final Translator translator = Translator
			.getTranslator(EvaluatorViewer.class);

	private Evaluator evaluator = null;
	private TextField evaluatorName;
	private HTMLTextPane description;
	private HTMLTextPane projectSummary;
	private FreeList fileList;
	private HTMLTextPane evaluatorFileComment;

	public EvaluatorViewer() {
		super();
		initUI();
	}

	private void initUI() {
		setNorth(evaluatorName = new TextField());
		TabbedPane tabbedPane = new TabbedPane();
		setCenter(tabbedPane);
		tabbedPane.add(new ScrollPane(description = new HTMLTextPane()),
				translator.i18n("Evaluator Description"));
		tabbedPane.add(new ScrollPane(projectSummary = new HTMLTextPane()),
				translator.i18n("Project Summary"));
		Panel filePanel = new Panel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
		tabbedPane.add(filePanel, translator.i18n("Evaluators File Summary"));

		filePanel.add(new ScrollPane(fileList = new FreeList()));
		fileList.connect("valueChanged", this, "fileChanged", Object.class);
		filePanel
				.add(new ScrollPane(evaluatorFileComment = new HTMLTextPane()));
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
		description.setText(evaluator.getDescription(ReportingFormat.HTML));
		projectSummary.setText(evaluator
				.getProjectComment(ReportingFormat.HTML));
		evaluatorFileComment.setText("");
		refreshFileList();
	}

	private void refreshFileList() {
		fileList.removeAll();
		System.out.println("New files...");
		Hashtable<Object, Object> listData = new Hashtable<Object, Object>();
		for (File file : evaluator.getFiles()) {
			System.out.println(file);
			listData.put(file.getName(), file);
		}
		fileList.setListData(listData);
	}

	@Slot
	public void fileChanged(Object o) {
		File file = (File) o;
		System.out.println(file);
		evaluatorFileComment.setText(evaluator.getFileComment(file,
				ReportingFormat.HTML));
	}
}
