package com.puresol.gui.coding.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import javax.i18n4j.Translator;
import javax.swing.border.TitledBorder;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.FreeList;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Slot;

import com.puresol.coding.CodeRange;
import com.puresol.coding.Duplication;
import com.puresol.coding.DuplicationScanner;

public class DuplicationBrowser extends BorderLayoutWidget {

	private static final long serialVersionUID = -6908062102128593972L;

	private static final Translator translator = Translator
			.getTranslator(DuplicationBrowser.class);

	private DuplicationScanner scanner;
	private FreeList duplicationList = null;
	private DuplicationViewer viewer;
	private boolean color = true;

	public DuplicationBrowser() {
		super();
		initUI();
	}

	public DuplicationBrowser(DuplicationScanner scanner) {
		super();
		initUI();
		setScanner(scanner);
	}

	private void initUI() {
		setCenter(viewer = new DuplicationViewer());
		setWest(new ScrollPane(duplicationList = new FreeList()));
		duplicationList.setBorder(new TitledBorder(translator
				.i18n("Possible Duplications")));
		duplicationList.connect("valueChanged", this, "setDuplication",
				Object.class);
	}

	public void setScanner(DuplicationScanner scanner) {
		this.scanner = scanner;
		refresh();
	}

	public void refresh() {
		ArrayList<Duplication> duplications = scanner.getDuplications();
		Collections.sort(duplications, Collections.reverseOrder());
		Hashtable<Object, Object> entries = new Hashtable<Object, Object>();
		for (Duplication duplication : duplications) {
			entries.put(createListEntry(duplication), duplication);
		}
		duplicationList.setListData(entries);
	}

	private String createListEntry(Duplication duplication) {
		CodeRange left = duplication.getLeft();
		CodeRange right = duplication.getRight();
		color = !color;
		String entry = "<html><body>";
		if (color) {
			entry += "<table bgcolor=\"#c0c0c0\">";
		} else {
			entry += "<table bgcolor=\"#ffffff\">";
		}
		entry += "<tr><td>" + left.getFile() + ":" + left.getStart() + "-"
				+ left.getStop() + "</td></tr>";
		entry += "<tr><td>" + right.getFile() + ":" + right.getStart() + "-"
				+ right.getStop() + "</td></tr>";
		entry += "<tr><td><b>" + left.getName() + " <--> " + right.getName()
				+ "</b></td></tr>";
		entry += "<tr><td><b>" + duplication.getMatchSize() + " ("
				+ Math.round(duplication.getCorrelation() * 1000.0) / 10.0
				+ "%)</b></td></tr>";
		entry += "</table>";
		entry += "</body></html>";
		return entry;
	}

	@Slot
	public void setDuplication(Object object) {
		viewer.setDuplication((Duplication) object);
	}
}
