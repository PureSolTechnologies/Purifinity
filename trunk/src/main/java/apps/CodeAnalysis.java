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

import javax.i18n4java.Translator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swingx.Application;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.Button;
import javax.swingx.MemoryMonitor;
import javax.swingx.Menu;
import javax.swingx.MenuBar;
import javax.swingx.MenuItem;
import javax.swingx.ToolBar;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressWindow;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleException;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.gui.PureSolApplication;
import com.puresol.gui.coding.NewProjectAnalyserDialog;
import com.puresol.gui.coding.ProjectAnalysisBrowser;
import com.puresol.gui.osgi.BundleConfigurationDialog;
import com.puresol.gui.osgi.BundleManager;
import com.puresol.osgi.OSGi;
import com.puresol.osgi.OSGiException;
import com.puresol.osgi.OSGiFrameworkManager;

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

	private OSGi osgi;
	private ProjectAnalysisBrowser browser = null;

	private ProjectAnalyzer analyser = null;

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
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu(translator.i18n("File"));
		Menu optionsMenu = new Menu(translator.i18n("Options"));

		MenuItem newWorkspace = new MenuItem("New Workspace...");
		newWorkspace.connect("start", this, "newWorkspace");

		MenuItem openWorkspace = new MenuItem("Open Workspace...");
		openWorkspace.connect("start", this, "openWorkspace");

		MenuItem updateWorkspace = new MenuItem("Update Workspace");
		updateWorkspace.connect("start", this, "updateWorkspace");

		MenuItem createEvaluatorHTML = new MenuItem("Create HTML Project...");
		createEvaluatorHTML.connect("start", this, "createHTMLProject");

		MenuItem exit = new MenuItem("Exit");
		exit.connect("start", this, "quit");

		MenuItem pluginManager = new MenuItem("Plugin Manager...");
		pluginManager.connect("start", this, "pluginManager");

		MenuItem pluginConfiguration = new MenuItem("Plugin Configuration...");
		pluginConfiguration.connect("start", this, "pluginConfiguration");

		menuBar.add(fileMenu);
		fileMenu.add(newWorkspace);
		fileMenu.addSeparator();
		fileMenu.add(openWorkspace);
		fileMenu.add(updateWorkspace);
		fileMenu.addSeparator();
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
		BorderLayoutWidget widget = new BorderLayoutWidget();
		setContentPane(widget);

		ToolBar toolbar = new ToolBar();
		Button newWorkspace = new Button(translator.i18n("New Workspace..."));
		newWorkspace.connect("start", this, "newWorkspace");
		toolbar.add(newWorkspace);

		Button openWorkspace = new Button(translator.i18n("Open Workspace..."));
		openWorkspace.connect("start", this, "openWorkspace");
		toolbar.add(openWorkspace);

		Button updateWorkspace = new Button(translator.i18n("Update Workspace"));
		updateWorkspace.connect("start", this, "updateWorkspace");
		toolbar.add(updateWorkspace);

		widget.setNorth(toolbar);

		widget.setCenter(browser = new ProjectAnalysisBrowser());
		widget.setSouth(new MemoryMonitor());
	}

	@Slot
	void updateWorkspace() {
		Application.showNotImplementedMessage();
	}

	@Slot
	void newWorkspace() {
		NewProjectAnalyserDialog dialog = new NewProjectAnalyserDialog();
		if (!dialog.run()) {
			return;
		}
		setSubtitle(dialog.getWorkspaceDirectory().toString());
		analyser = ProjectAnalyzer.create(dialog.getSourceDirectory(),
				dialog.getWorkspaceDirectory());
		if (analyser != null) {
			ProgressWindow progress = new ProgressWindow(analyser);
			progress.connect("finished", this, "refresh");
			progress.run();
		} else {
			JOptionPane
					.showMessageDialog(this, translator
							.i18n("Could not create new analyser workspace!"),
							translator.i18n("Error"), JOptionPane.ERROR_MESSAGE);
		}
	}

	@Slot
	void openWorkspace() {
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		file.setName(translator.i18n("Open Analysis Workspace"));
		if (file.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) {
			return;
		}
		analyser = ProjectAnalyzer.open(file.getSelectedFile());
		refresh();
	}

	@Slot
	void createHTMLProject() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = chooser.showSaveDialog(Application.getInstance());
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		Application.showNotImplementedMessage();
	}

	@Slot
	void pluginManager() {
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

	@Slot
	void pluginConfiguration() {
		new BundleConfigurationDialog(CodeAnalysis.class.getName()).run();
	}

	@Slot
	void refresh() {
		setSubtitle(analyser.getWorkspaceDirectory().getPath());
		browser.setProjectAnalyser(analyser);
	}

	@Slot
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

	public static void main(String[] args) {
		new CodeAnalysis().run();
	}
}
