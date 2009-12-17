package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.i18n4j.Translator;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swingx.FreeList;
import javax.swingx.HTMLTextPane;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Slot;

import com.puresol.coding.CodeRange;
import com.puresol.coding.HTMLAnalysisReport;
import com.puresol.coding.CodeRangeMetrics;
import com.puresol.coding.ProjectAnalyser;
import com.puresol.coding.QualityLevel;

public class CodeRangeAnalysisBrowser extends Panel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeAnalysisBrowser.class);

	private ProjectAnalyser project = null;
	private FreeList fileList = null;
	private FreeList codeRangeList = null;
	private HTMLTextPane results = null;
	private Hashtable<CodeRangeMetrics, HTMLAnalysisReport> reports = new Hashtable<CodeRangeMetrics, HTMLAnalysisReport>();

	public CodeRangeAnalysisBrowser() {
		super();
		initUI();
	}

	public CodeRangeAnalysisBrowser(ProjectAnalyser project) {
		super();
		initUI();
		setProjectAnalyser(project);
	}

	private void initUI() {
		setLayout(new BorderLayout());

		Panel listsPanel = new Panel();
		listsPanel.setLayout(new BoxLayout(listsPanel, BoxLayout.Y_AXIS));

		fileList = new FreeList();
		fileList.connect("indexChanged", this, "showFile", int.class);
		fileList.setBorder(new TitledBorder(translator.i18n("Source Files")));
		listsPanel.add(new ScrollPane(fileList));

		codeRangeList = new FreeList();
		codeRangeList.connect("indexChanged", this, "showCodeRange", int.class);
		codeRangeList.setBorder(new TitledBorder(translator.i18n("Modules")));
		listsPanel.add(new ScrollPane(codeRangeList));

		add(listsPanel, BorderLayout.WEST);
		results = new HTMLTextPane();
		add(new ScrollPane(results), BorderLayout.CENTER);
	}

	public void setProjectAnalyser(ProjectAnalyser project) {
		this.project = project;
		updateFiles();
	}

	private void updateFiles() {
		codeRangeList.removeAll();
		fileList.removeAll();
		if (project == null) {
			return;
		}
		Hashtable<Object, Object> listData = new Hashtable<Object, Object>();
		Set<File> files = project.getFiles();
		for (File file : files) {
			ArrayList<CodeRange> ranges = project.getCodeRanges(file);
			for (CodeRange range : ranges) {
				calculateReport(project.getMetrics(file, range));
			}
			QualityLevel quality = getQualityLevel(file);
			String html = "<html><body>";
			if (quality == QualityLevel.HIGH) {
				html += "<table width=\"100%\" bgcolor=\"#00ff00\">";
			} else if (quality == QualityLevel.MEDIUM) {
				html += "<table width=\"100%\" bgcolor=\"#ffff00\">";
			} else {
				html += "<table width=\"100%\" bgcolor=\"#ff0000\">";
			}
			html += "<tr><td>" + file.getPath()
					+ "</td></tr></table></body></html>";
			listData.put(html, file);

		}
		fileList.setListData(listData);
	}

	private void updateCodeRanges(File file) {
		codeRangeList.removeAll();
		if (project == null) {
			return;
		}
		Hashtable<Object, Object> listData = new Hashtable<Object, Object>();
		ArrayList<CodeRange> ranges = project.getCodeRanges(file);
		for (CodeRange range : ranges) {
			String html = "<html><body>";
			QualityLevel quality = project.getMetrics(file, range)
					.getQualityLevel();
			if (quality == QualityLevel.HIGH) {
				html += "<table width=\"100%\" bgcolor=\"#00ff00\">";
			} else if (quality == QualityLevel.MEDIUM) {
				html += "<table width=\"100%\" bgcolor=\"#ffff00\">";
			} else {
				html += "<table width=\"100%\" bgcolor=\"#ff0000\">";
			}
			html += "<tr><td>" + range.getType().getName() + ":"
					+ range.getName() + "</td></tr></table></body></html>";
			listData.put(html, range);
		}
		codeRangeList.setListData(listData);
	}

	private void calculateReport(CodeRangeMetrics metrics) {
		reports.put(metrics, new HTMLAnalysisReport(metrics));
	}

	private QualityLevel getQualityLevel(File file) {
		QualityLevel level = QualityLevel.HIGH;
		for (CodeRange range : project.getCodeRanges(file)) {
			CodeRangeMetrics metrics = project.getMetrics(file, range);
			if (level.getLevel() > metrics.getQualityLevel().getLevel()) {
				level = metrics.getQualityLevel();
			}
		}
		return level;
	}

	@Slot
	public void showFile(int index) {
		results.setText("");
		File file = (File) fileList.getSelectedValue();
		updateCodeRanges(file);
	}

	@Slot
	public void showCodeRange(int index) {
		File file = (File) fileList.getSelectedValue();
		if (file == null) {
			return;
		}
		CodeRange codeRange = (CodeRange) codeRangeList.getSelectedValue();
		if (codeRange == null) {
			return;
		}
		CodeRangeMetrics metrics = project.getMetrics(file, codeRange);
		results.setText(reports.get(metrics).getReport());
	}
}
