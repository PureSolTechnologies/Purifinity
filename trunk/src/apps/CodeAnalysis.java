/***************************************************************************
 *
 *   CodeAnalysis.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package apps;

import java.io.File;

import javax.i18n4j.Translator;
import javax.swing.JFileChooser;
import javax.swingx.Application;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.MemoryMonitor;
import javax.swingx.Menu;
import javax.swingx.MenuBar;
import javax.swingx.MenuItem;
import javax.swingx.ToggleButton;
import javax.swingx.ToolBar;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressWindow;

import com.puresol.coding.ProjectAnalyser;
import com.puresol.coding.analysis.CodeEvaluationSystem;
import com.puresol.coding.analysis.out.TSVFile;
import com.puresol.coding.analysis.reports.HTMLAnalysisProject;
import com.puresol.gui.PureSolApplication;
import com.puresol.gui.coding.analysis.ProjectAnalysisBrowser;

/**
 * This is PureSolTechnologies' code analysis tool for automated source code
 * validation and evaluation.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CodeAnalysis extends PureSolApplication {

	private static final long serialVersionUID = -3002673096551916032L;

	private static final Translator translator = Translator
			.getTranslator(CodeAnalysis.class);

	private ProjectAnalysisBrowser browser = null;
	private ProjectAnalyser analyser = null;
	private ToolBar toolBar = null;
	private ToggleButton slocButton = null;
	private ToggleButton mcCabeButton = null;
	private ToggleButton halsteadButton = null;
	private ToggleButton entropyButton = null;
	private ToggleButton maintainabilityButton = null;

	public CodeAnalysis() {
		super("Code Analysis", "v0.0.1");
		initMenu();
		initDesktop();
	}

	private void initMenu() {
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu(translator.i18n("File"));
		Menu projectMenu = new Menu(translator.i18n("Project"));

		MenuItem createTSV = new MenuItem("Save as TSV...");
		createTSV.connect("start", this, "createTSV");

		MenuItem createHTML = new MenuItem("Create HTML...");
		createHTML.connect("start", this, "createHTML");

		MenuItem exit = new MenuItem("Exit");
		exit.connect("start", this, "quit");

		MenuItem analyse = new MenuItem("Analyse...");
		analyse.connect("start", this, "analyse");

		menuBar.add(fileMenu);
		fileMenu.add(createTSV);
		fileMenu.add(createHTML);
		fileMenu.addSeparator();
		fileMenu.add(exit);

		menuBar.add(projectMenu);
		projectMenu.add(analyse);

		setJMenuBar(menuBar);
	}

	private void initDesktop() {
		BorderLayoutWidget widget = new BorderLayoutWidget();
		setContentPane(widget);

		browser = new ProjectAnalysisBrowser();
		toolBar = new ToolBar();
		toolBar.add(slocButton = new ToggleButton("SLOC"));
		toolBar.add(mcCabeButton = new ToggleButton("McCabe"));
		toolBar.add(halsteadButton = new ToggleButton("Halstead"));
		toolBar.add(entropyButton = new ToggleButton("Entropy"));
		toolBar
				.add(maintainabilityButton = new ToggleButton("Maintainability"));

		slocButton.setSelected(CodeEvaluationSystem.isEvaluateSLOCMetric());
		mcCabeButton.setSelected(CodeEvaluationSystem.isEvaluateMcCabeMetric());
		halsteadButton.setSelected(CodeEvaluationSystem
				.isEvaluateHalsteadMetric());
		entropyButton.setSelected(CodeEvaluationSystem
				.isEvaluateEntropyMetric());
		maintainabilityButton.setSelected(CodeEvaluationSystem
				.isEvaluateMaintainabilityIndex());

		slocButton.connect("valueChanged", this, "setEvaluateSLOCMetric",
				Boolean.class);
		mcCabeButton.connect("valueChanged", this, "setEvaluateMcCabeMetric",
				Boolean.class);
		halsteadButton.connect("valueChanged", this,
				"setEvaluateHalsteadMetric", Boolean.class);
		entropyButton.connect("valueChanged", this, "setEvaluateEntropyMetric",
				Boolean.class);
		maintainabilityButton.connect("valueChanged", this,
				"setEvaluateMaintainability", Boolean.class);

		widget.setCenter(browser);
		widget.setNorth(toolBar);
		widget.setSouth(new MemoryMonitor());
	}

	@Slot
	public void createTSV() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(Application.getInstance());
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		TSVFile.create(chooser.getSelectedFile(), browser.getProjectAnalyser());
	}

	@Slot
	public void createHTML() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = chooser.showSaveDialog(Application.getInstance());
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		HTMLAnalysisProject.create(chooser.getSelectedFile(), browser
				.getProjectAnalyser());
	}

	@Slot
	public void analyse() {
		JFileChooser directory = new JFileChooser();
		directory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = directory.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		File file = directory.getSelectedFile();
		setApplicationSubtitle(file.getPath());
		analyser = new ProjectAnalyser(file, "**/*");
		ProgressWindow progress = new ProgressWindow(analyser);
		progress.connect("finished", this, "refresh");
		progress.run();
	}

	@Slot
	public void refresh() {
		browser.setProjectAnalyser(analyser);
	}

	@Slot
	public void setEvaluateSLOCMetric(Boolean value) {
		CodeEvaluationSystem.setEvaluateSLOCMetric(value);
		browser.refresh();
	}

	@Slot
	public void setEvaluateMcCabeMetric(Boolean value) {
		CodeEvaluationSystem.setEvaluateMcCabeMetric(value);
		browser.refresh();
	}

	@Slot
	public void setEvaluateHalsteadMetric(Boolean value) {
		CodeEvaluationSystem.setEvaluateHalsteadMetric(value);
		browser.refresh();
	}

	@Slot
	public void setEvaluateEntropyMetric(Boolean value) {
		CodeEvaluationSystem.setEvaluateEntropyMetric(value);
		browser.refresh();
	}

	@Slot
	public void setEvaluateMaintainability(Boolean value) {
		CodeEvaluationSystem.setEvaluateMaintainabilityIndex(value);
		browser.refresh();
	}

	public static void main(String[] args) {
		new CodeAnalysis().run();
	}
}
