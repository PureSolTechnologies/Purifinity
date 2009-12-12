package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.i18n4j.Translator;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swingx.FreeList;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Slot;

import com.puresol.coding.CodeRange;
import com.puresol.coding.HTMLAnalysisReport;
import com.puresol.coding.ProjectAnalyser;
import com.puresol.coding.QualityLevel;

public class ProjectAnalysisBrowser extends Panel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisBrowser.class);

	private ProjectAnalyser project = null;
	private FreeList codeRanges = null;
	private JTextPane results = null;
	private ScrollPane resultsScroller = null;
	private Hashtable<CodeRange, HTMLAnalysisReport> reports = new Hashtable<CodeRange, HTMLAnalysisReport>();

	public ProjectAnalysisBrowser() {
		super();
		initUI();
	}

	public ProjectAnalysisBrowser(ProjectAnalyser project) {
		super();
		initUI();
		setProjectAnalyser(project);
	}

	private void initUI() {
		setLayout(new BorderLayout());
		codeRanges = new FreeList();
		codeRanges.connect("indexChanged", this, "showResults", int.class);
		codeRanges.setBorder(new TitledBorder(translator.i18n("Source File")));
		resultsScroller = new ScrollPane(codeRanges);
		add(resultsScroller, BorderLayout.WEST);
		results = new JTextPane();

		HTMLEditorKit kit = new HTMLEditorKit();
		results.setEditorKit(kit);
		Document doc = kit.createDefaultDocument();
		results.setDocument(doc);
		results.setEditable(false);

		add(new ScrollPane(results), BorderLayout.CENTER);
	}

	public void setProjectAnalyser(ProjectAnalyser project) {
		this.project = project;
		update();
	}

	public void update() {
		if (project == null) {
			return;
		}
		Hashtable<Object, Object> listData = new Hashtable<Object, Object>();
		Set<File> files = project.getFiles();
		for (File file : files) {
			ArrayList<CodeRange> ranges = project.getCodeRanges(file);
			for (CodeRange range : ranges) {
				calculateReport(range);
				String html = "<html><body>";
				if (reports.get(range).getQualityLevel() == QualityLevel.HIGH) {
					html += "<table width=\"100%\" bgcolor=\"#00ff00\">";
				} else if (reports.get(range).getQualityLevel() == QualityLevel.MEDIUM) {
					html += "<table width=\"100%\" bgcolor=\"#ffff00\">";
				} else {
					html += "<table width=\"100%\" bgcolor=\"#ff0000\">";
				}
				html += "<tr><td>" + file.getPath() + "</td></tr><tr><td>"
						+ range.getType().getName() + ":" + range.getName()
						+ "</td></tr></table></body></html>";
				listData.put(html, range);

			}
		}
		codeRanges.setListData(listData);
	}

	private void calculateReport(CodeRange range) {
		reports.put(range, new HTMLAnalysisReport(range));
	}

	@Slot
	public void showResults(int index) {
		Object[] objs = codeRanges.getSelectedValues();
		if (objs.length != 1) {
			results.setText("");
			return;
		}
		CodeRange range = (CodeRange) objs[0];
		results.setText(reports.get(range).getReport());
	}
}
