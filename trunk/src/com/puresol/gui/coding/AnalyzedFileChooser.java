package com.puresol.gui.coding;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swingx.FreeList;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Signal;
import javax.swingx.connect.Slot;

import com.puresol.coding.analysis.ProjectAnalyzer;

/**
 * This GUI element is for selecting analyzed files from a project analyzer.
 * Some signals bind actions within this element to outer actions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyzedFileChooser extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Translator translator = Translator
			.getTranslator(AnalyzedFileChooser.class);

	private ProjectAnalyzer projectAnalyser = null;

	private final FreeList fileList = new FreeList();

	public AnalyzedFileChooser() {
		super();
		initUI();
	}

	public AnalyzedFileChooser(ProjectAnalyzer projectAnalyser) {
		super();
		this.projectAnalyser = projectAnalyser;
		initUI();
	}

	private void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		fileList.connect("valueChanged", this, "fileSelected", Object.class);
		ScrollPane fileScroller = new ScrollPane(fileList);
		fileScroller.setBorder(new TitledBorder(translator
				.i18n("Analyzed Files")));
		add(fileScroller);
	}

	public ProjectAnalyzer getProjectAnlayser() {
		return projectAnalyser;
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
		refresh();
	}

	public void refresh() {
		if (projectAnalyser != null) {
			java.util.List<File> files = projectAnalyser.getFiles();
			Collections.sort(files);
			Map<Object, Object> listData = new HashMap<Object, Object>();
			for (File file : files) {
				listData.put(file.toString(), file);
			}
			fileList.setListData(listData);
		} else {
			fileList.removeAll();
		}
	}

	@Slot
	void fileSelected(Object o) {
		fileChanged((File) o);
	}

	@Signal
	public void fileChanged(File file) {
		connectionManager.emitSignal("fileChanged", file);
	}
}
