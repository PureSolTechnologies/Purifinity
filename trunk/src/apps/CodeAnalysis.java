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

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.i18n4j.Translator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swingx.Application;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.MemoryMonitor;
import javax.swingx.Menu;
import javax.swingx.MenuBar;
import javax.swingx.MenuItem;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressWindow;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.CodeEvaluator;
import com.puresol.coding.evaluator.EvaluationReport;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.evaluator.metric.report.HTMLMetricsProject;
import com.puresol.coding.evaluator.metric.report.tsv.TSVFile;
import com.puresol.gui.PureSolApplication;
import com.puresol.gui.coding.analysis.MetricSelectionToolBar;
import com.puresol.gui.coding.analysis.ProjectAnalysisBrowser;
import com.puresol.reporting.ReportingFormat;
import com.puresol.utils.Files;

/**
 * This is PureSolTechnologies' code analysis tool for automated source code
 * validation and evaluation.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CodeAnalysis extends PureSolApplication {

	private static final long serialVersionUID = -3002673096551916032L;

	private static final Logger logger = Logger.getLogger(CodeAnalysis.class);
	private static final Translator translator = Translator
			.getTranslator(CodeAnalysis.class);

	private ProjectAnalyser analyser = null;
	private ProjectAnalysisBrowser browser = null;
	private MetricSelectionToolBar toolBar = null;

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

		MenuItem createMetricsHTML = new MenuItem("Create Metrics HTML...");
		createMetricsHTML.connect("start", this, "createMetricsHTML");

		MenuItem createEvaluatorHTML = new MenuItem("Create Evaluator HTML...");
		createEvaluatorHTML.connect("start", this, "createEvaluatorHTML");

		MenuItem exit = new MenuItem("Exit");
		exit.connect("start", this, "quit");

		MenuItem analyse = new MenuItem("Analyse...");
		analyse.connect("start", this, "analyse");

		menuBar.add(fileMenu);
		fileMenu.add(createTSV);
		fileMenu.add(createMetricsHTML);
		fileMenu.add(createEvaluatorHTML);
		fileMenu.addSeparator();
		fileMenu.add(exit);

		menuBar.add(projectMenu);
		projectMenu.add(analyse);

		setJMenuBar(menuBar);
	}

	private void initDesktop() {
		BorderLayoutWidget widget = new BorderLayoutWidget();
		setContentPane(widget);

		toolBar = new MetricSelectionToolBar();
		toolBar.connect("refresh", this, "refresh");

		widget.setNorth(toolBar);
		widget.setCenter(browser = new ProjectAnalysisBrowser());
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
	public void createMetricsHTML() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = chooser.showSaveDialog(Application.getInstance());
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		HTMLMetricsProject.create(chooser.getSelectedFile(), browser
				.getProjectAnalyser());
		try {
			Desktop.getDesktop().browse(
					new URI("file://"
							+ Files.addPaths(chooser.getSelectedFile(),
									new File("index.html"))));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Slot
	public void createEvaluatorHTML() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = chooser.showSaveDialog(Application.getInstance());
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		CodeEvaluator evaluator = browser.getCodeEvaluator();
		if (evaluator != null) {
			try {
				EvaluationReport.report(evaluator, chooser.getSelectedFile(),
						ReportingFormat.HTML);
				Desktop.getDesktop().browse(
						new URI("file://"
								+ Files.addPaths(chooser.getSelectedFile(),
										new File("index.html"))));
			} catch (UnsupportedReportingFormatException e) {
				JOptionPane.showMessageDialog(Application.getInstance(),
						translator.i18n("No report generation possible!")
								+ "\n" + e.getMessage(), translator
								.i18n("Error"), JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} catch (URISyntaxException e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			JOptionPane
					.showMessageDialog(
							Application.getInstance(),
							translator
									.i18n("No report generation possible!\nNo evaluation was performed."),
							translator.i18n("Error"), JOptionPane.ERROR_MESSAGE);
		}
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

	public static void main(String[] args) {
		new CodeAnalysis().run();
	}
}
