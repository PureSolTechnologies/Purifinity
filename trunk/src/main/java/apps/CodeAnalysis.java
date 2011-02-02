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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.i18n4java.Translator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleException;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.EvaluatorASCIIExport;
import com.puresol.coding.reporting.HTMLProjectAnalysisCreator;
import com.puresol.filefilter.CSVFilter;
import com.puresol.filefilter.ExcelFilter;
import com.puresol.filefilter.TSVFilter;
import com.puresol.gui.Application;
import com.puresol.gui.MemoryMonitor;
import com.puresol.gui.PureSolApplication;
import com.puresol.gui.coding.NewProjectAnalyserDialog;
import com.puresol.gui.coding.ProjectAnalysisBrowser;
import com.puresol.gui.osgi.BundleConfigurationDialog;
import com.puresol.gui.osgi.BundleManager;
import com.puresol.gui.progress.FinishListener;
import com.puresol.gui.progress.ProgressObservable;
import com.puresol.gui.progress.ProgressWindow;
import com.puresol.osgi.OSGi;
import com.puresol.osgi.OSGiException;
import com.puresol.osgi.OSGiFrameworkManager;

/**
 * This is PureSolTechnologies' code analysis tool for automated source code
 * validation and evaluation.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CodeAnalysis extends PureSolApplication implements FinishListener {

	private static final long serialVersionUID = -3002673096551916032L;

	private static final Logger logger = Logger.getLogger(CodeAnalysis.class);
	private static final Translator translator = Translator
			.getTranslator(CodeAnalysis.class);

	private final JMenuItem newWorkspace = new JMenuItem("New Workspace...");
	private final JMenuItem openWorkspace = new JMenuItem("Open Workspace...");
	private final JMenuItem updateWorkspace = new JMenuItem("Update Workspace");
	private final JMenuItem exportValues = new JMenuItem("Export Values...");
	private final JMenuItem createEvaluatorHTML = new JMenuItem(
			"Create HTML Project...");
	private final JMenuItem exit = new JMenuItem("Exit");
	private final JMenuItem pluginManager = new JMenuItem("Plugin Manager...");
	private final JMenuItem pluginConfiguration = new JMenuItem(
			"Plugin Configuration...");

	private final JButton newWorkspaceButton = new JButton(
			translator.i18n("New Workspace..."));
	private final JButton openWorkspaceButton = new JButton(
			translator.i18n("Open Workspace..."));
	private final JButton updateWorkspaceButton = new JButton(
			translator.i18n("Update Workspace"));

	private final ProjectAnalysisBrowser browser = new ProjectAnalysisBrowser();

	private OSGi osgi;
	private ProjectAnalyzer analyzer = null;

	public CodeAnalysis() {
		super("Code Analysis", "v0.0.1");
		startOSGi();
		initMenu();
		initDesktop();
	}

	private void startOSGi() {
		try {
			osgi = OSGiFrameworkManager.getInstance();
			osgi.start();
		} catch (OSGiException e) {
			logger.error(e.getMessage(), e);
			Application.showStandardErrorMessage(translator
					.i18n("An error within the plugin system occured!"), e);
			throw new RuntimeException(e.getMessage());
		} catch (BundleException e) {
			logger.error(e.getMessage(), e);
			Application.showStandardErrorMessage(translator
					.i18n("An error within the plugin system occured!"), e);
			throw new RuntimeException(e.getMessage());
		}
	}

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu(translator.i18n("File"));
		JMenu optionsMenu = new JMenu(translator.i18n("Options"));

		newWorkspace.addActionListener(this);
		openWorkspace.addActionListener(this);
		updateWorkspace.addActionListener(this);
		exportValues.addActionListener(this);
		createEvaluatorHTML.addActionListener(this);
		exit.addActionListener(this);
		pluginManager.addActionListener(this);
		pluginConfiguration.addActionListener(this);

		menuBar.add(fileMenu);
		fileMenu.add(newWorkspace);
		fileMenu.addSeparator();
		fileMenu.add(openWorkspace);
		fileMenu.add(updateWorkspace);
		fileMenu.addSeparator();
		fileMenu.add(exportValues);
		fileMenu.add(createEvaluatorHTML);
		fileMenu.addSeparator();
		fileMenu.add(exit);

		menuBar.add(optionsMenu);
		optionsMenu.add(pluginManager);
		optionsMenu.addSeparator();
		optionsMenu.add(pluginConfiguration);

		setJMenuBar(menuBar);
	}

	private void initDesktop() {
		JPanel widget = new JPanel();
		widget.setLayout(new BorderLayout());
		setContentPane(widget);

		JToolBar toolbar = new JToolBar();
		toolbar.add(newWorkspaceButton);
		toolbar.add(openWorkspaceButton);
		toolbar.add(updateWorkspaceButton);

		newWorkspaceButton.addActionListener(this);
		openWorkspaceButton.addActionListener(this);
		updateWorkspaceButton.addActionListener(this);

		widget.add(toolbar, BorderLayout.NORTH);
		widget.add(browser, BorderLayout.CENTER);
		widget.add(new MemoryMonitor(), BorderLayout.SOUTH);
	}

	private void newWorkspace() {
		NewProjectAnalyserDialog dialog = new NewProjectAnalyserDialog();
		if (!dialog.run()) {
			return;
		}
		setSubtitle(dialog.getWorkspaceDirectory().toString());
		analyzer = ProjectAnalyzer.create(dialog.getSourceDirectory(),
				dialog.getWorkspaceDirectory());
		if (analyzer != null) {
			ProgressWindow progress = new ProgressWindow(this, true);
			progress.addFinishListener(this);
			progress.runAsynchronous(analyzer);
		} else {
			JOptionPane
					.showMessageDialog(this, translator
							.i18n("Could not create new analyser workspace!"),
							translator.i18n("Error"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void openWorkspace() {
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		file.setName(translator.i18n("Open Analysis Workspace"));
		if (file.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) {
			return;
		}
		analyzer = ProjectAnalyzer.open(file.getSelectedFile());
		refresh();
	}

	private void updateWorkspace() {
		if (analyzer != null) {
			ProgressWindow progress = new ProgressWindow(this, true);
			progress.addFinishListener(this);
			progress.runAsynchronous(analyzer);
		} else {
			JOptionPane.showMessageDialog(this,
					translator.i18n("No workspace is open for update!!"),
					translator.i18n("Error"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void exportValues() {
		JFileChooser chooser = new JFileChooser();
		ExcelFilter excelFilter = new ExcelFilter();
		CSVFilter csvFilter = new CSVFilter();
		TSVFilter tsvFilter = new TSVFilter();
		chooser.setFileFilter(excelFilter);
		chooser.setFileFilter(csvFilter);
		chooser.setFileFilter(tsvFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = chooser.showOpenDialog(Application.getInstance());
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		FileFilter filter = chooser.getFileFilter();
		if (filter == tsvFilter) {
			EvaluatorASCIIExport export = new EvaluatorASCIIExport(
					chooser.getSelectedFile(), analyzer, "\t");
			ProgressWindow progressWindow = new ProgressWindow(this, true);
			progressWindow.addFinishListener(this);
			progressWindow.runAsynchronous(export);
		} else if (filter == csvFilter) {
			EvaluatorASCIIExport export = new EvaluatorASCIIExport(
					chooser.getSelectedFile(), analyzer, ",");
			ProgressWindow progressWindow = new ProgressWindow(this, true);
			progressWindow.addFinishListener(this);
			progressWindow.runAsynchronous(export);
		} else if (filter == excelFilter) {
			Application.showNotImplementedMessage();
		}
	}

	private void createHTMLProject() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = chooser.showSaveDialog(Application.getInstance());
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		HTMLProjectAnalysisCreator creator = new HTMLProjectAnalysisCreator(
				analyzer, chooser.getSelectedFile());
		ProgressWindow progress = new ProgressWindow(this, true);
		progress.runAsynchronous(creator);
	}

	private void pluginManager() {
		if (osgi.isStarted()) {
			new BundleManager(osgi.getContext()).run();
		} else {
			JOptionPane
					.showConfirmDialog(
							getInstance(),
							translator
									.i18n("No plugin system was started. There is nothing to manage."),
							translator.i18n("Information"),
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void pluginConfiguration() {
		new BundleConfigurationDialog(CodeAnalysis.class.getName())
				.setVisible(true);
	}

	private void refresh() {
		setSubtitle(analyzer.getWorkspaceDirectory().getPath());
		browser.setProjectAnalyser(analyzer);
	}

	@Override
	public void quit() {
		try {
			osgi.stop();
			osgi = null;
		} catch (BundleException e) {
			logger.error(e.getMessage(), e);
		}
		super.quit();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ((e.getSource() == newWorkspace)
				|| (e.getSource() == newWorkspaceButton)) {
			newWorkspace();
		} else if ((e.getSource() == openWorkspace)
				|| (e.getSource() == openWorkspaceButton)) {
			openWorkspace();
		} else if ((e.getSource() == updateWorkspace)
				|| (e.getSource() == updateWorkspaceButton)) {
			updateWorkspace();
		} else if (e.getSource() == exportValues) {
			exportValues();
		} else if (e.getSource() == createEvaluatorHTML) {
			createHTMLProject();
		} else if (e.getSource() == exit) {
			quit();
		} else if (e.getSource() == pluginManager) {
			pluginManager();
		} else if (e.getSource() == pluginConfiguration) {
			pluginConfiguration();
		} else {
			super.actionPerformed(e);
		}
	}

	@Override
	public void finished(ProgressObservable o) {
		refresh();
	}

	@Override
	public void terminated(ProgressObservable o) {
		refresh();
	}

	public static void main(String[] args) {
		new CodeAnalysis().run();
	}

}
